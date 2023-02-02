package com.agenda.up.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.databinding.ActivityEditarTarefaBinding
import com.agenda.up.repositorio.EditarRepositorio
import com.agenda.up.viewmodel.editar.EditarViewModel
import com.agenda.up.viewmodel.editar.EditarViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class EditarTarefa : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityEditarTarefaBinding
    private lateinit var viewModel: EditarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarTarefaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            EditarViewModelFactory(EditarRepositorio())
        )[EditarViewModel::class.java]

        val ano = binding.txtAno
        val mes = binding.txtMes
        val dia = binding.txtDia
        val hrs = binding.txtHrs
        val min = binding.txtMin
        val titulo = binding.editTitulo
        val descricao = binding.editDescricao
        val detalhes = binding.editDetalhes
        val grupoRadioPrioridades = binding.radioGroupPrioridades
        val checkDescricao = binding.checkBox
        val checkDetalhes = binding.checkBox2
        val checkprioridades = binding.checkBox3


        checkDescricaoDetalhes(
            descricao,
            detalhes,
            checkDescricao,
            checkDetalhes,
            checkprioridades,
            grupoRadioPrioridades
        )

        resgatarPropriedades(ano, mes, dia, hrs, min, titulo, descricao, detalhes)

        dailogCalendario()

        dailogHrs()

        binding.btAtualizar.setOnClickListener {
            val anoNovo = binding.txtAno.text.toString()
            val mesNovo = binding.txtMes.text.toString()
            val diaNovo = binding.txtDia.text.toString()
            val hrsNovo = binding.txtHrs.text.toString()
            val minNovo = binding.txtMin.text.toString()
            val tituloNovo = binding.editTitulo.text.toString()
            val descricaoNovo = binding.editDescricao.text.toString()
            val detalhesNovo = binding.editDetalhes.text.toString()
            val uid = (intent.extras!!.getInt("uid"))
            val prioridade1 = binding.btRadioSuave
            val prioridade2 = binding.btRadioNorapido
            val prioridade3 = binding.btRadioDuque
            val prioridadeSalva = checkDePrioridade(prioridade1, prioridade2, prioridade3)
            val lembrete = binding.checkBox4

            val diaAtual =  Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

            if (tituloNovo.isEmpty()) {
                binding.editTitulo.error = "Titulo obrigatorio!"
            } else {
                ativarAlarme(hrsNovo, minNovo, tituloNovo, lembrete, diaNovo, diaAtual)
                if ((lembrete.isChecked && diaNovo == diaAtual.toString()) || (!lembrete.isChecked && diaNovo != diaAtual.toString()) || (!lembrete.isChecked && diaNovo == diaAtual.toString())) {
                    viewModel.atualizarTarefa(
                        uid,
                        anoNovo,
                        mesNovo,
                        diaNovo,
                        hrsNovo.toInt(),
                        minNovo.toInt(),
                        tituloNovo,
                        descricaoNovo,
                        detalhesNovo,
                        prioridadeSalva,
                        this
                    )
                    val intent = Intent(this, TelaPrinscipal::class.java)
                    intent.putExtra("atualizarTelaEdit", true)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun ativarAlarme(
        txtHrsFinal: String,
        txtMinFinal: String,
        editTituloFinal: String,
        checkLembrete: CheckBox,
        diaFinal: String,
        diaAtual: Int
    ) {
        if (checkLembrete.isChecked){
            if (diaFinal == diaAtual.toString()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, txtHrsFinal.toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, txtMinFinal.toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, editTituloFinal)
                startActivity(intent)
            }else {
                alertDialogErroLembrete()
            }
        }
    }

    private fun alertDialogErroLembrete() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ops!")
        builder.setMessage(
            "por limitações, o lembrete só podera ser definido" +
                    " no dia do evento, estamos trabalhando para " +
                    "resolver isto em uma nova atualização, desculpe o transtorno!\n" +
                    "(voce ainda pode marcar a tarefa para este mesmo dia, sem o lembrete) "
        )
        builder.setPositiveButton("OK") { _, _ ->

        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun resgatarPropriedades(
        ano: TextView,
        mes: TextView,
        dia: TextView,
        hrs: TextView,
        min: TextView,
        titulo: TextInputEditText,
        descricao: TextInputEditText,
        detalhes: TextInputEditText
    ) {
        hrs.text = intent?.getStringExtra("hrs").toString()
        min.text = intent?.getStringExtra("min").toString()
        ano.setText(intent?.getStringExtra("ano")).toString()
        mes.text = intent?.getStringExtra("mes").toString()
        dia.setText(intent?.getStringExtra("dia")).toString()
        titulo.setText(intent?.getStringExtra("titulo")).toString()
        descricao.setText(intent?.getStringExtra("descricao")).toString()
        detalhes.setText(intent?.getStringExtra("detalhes")).toString()

    }

    private fun dailogHrs() {

        binding.viewHora.setOnClickListener {
            val c = Calendar.getInstance()
            val hrs = c.get(Calendar.HOUR)
            val min = c.get(Calendar.MINUTE)
            TimePickerDialog(this, this, hrs, min, true).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val mesCorrecao = p2 + 1
        val ano = binding.txtAno
        val mes = binding.txtMes
        val dia = binding.txtDia

        ano.text = p1.toString()
        mes.text = mesCorrecao.toString()
        dia.text = p3.toString()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val hrs = binding.txtHrs
        val min = binding.txtMin
        hrs.text = p1.toString()
        min.text = p2.toString()
    }

    private fun dailogCalendario() {
        binding.viewDia.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONDAY)
            val monthDay = c.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, this, year, month, monthDay).show()

        }
    }

    private fun checkDescricaoDetalhes(
        editDescricao: TextInputEditText,
        editDetalhes: TextInputEditText,
        checkDescricao: CheckBox,
        checkDetalhes: CheckBox,
        checkprioridades: CheckBox,
        grupoRadioPrioridades: RadioGroup
    ) {
        checkDescricao.setOnClickListener {
            if (checkDescricao.isChecked) {

                editDescricao.visibility = View.VISIBLE
            } else
                editDescricao.visibility = View.GONE
        }
        checkDetalhes.setOnClickListener {
            if (checkDetalhes.isChecked) {
                editDetalhes.visibility = View.VISIBLE
            } else
                editDetalhes.visibility = View.GONE
        }
        checkprioridades.setOnClickListener {
            if (checkprioridades.isChecked) {
                grupoRadioPrioridades.visibility = View.VISIBLE
            } else
                grupoRadioPrioridades.visibility = View.GONE
        }
    }

    private fun checkDePrioridade(
        prioridade1: RadioButton,
        prioridade2: RadioButton,
        prioridade3: RadioButton
    ): Int {

        val prioridade: Int =
            if (prioridade1.isChecked || prioridade2.isChecked || prioridade3.isChecked) {
                when (true) {
                    prioridade1.isChecked -> 3
                    prioridade2.isChecked -> 2
                    prioridade3.isChecked -> 1
                    else -> 4
                }
            } else 4
        return prioridade
    }

}