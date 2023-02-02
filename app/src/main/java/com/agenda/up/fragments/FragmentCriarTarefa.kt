package com.agenda.up.fragments

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.DatePickerFragment
import com.agenda.up.R
import com.agenda.up.databinding.FragLayoutCriarTarefaBinding
import com.agenda.up.repositorio.CriarTarefaRepositorio
import com.agenda.up.viewmodel.criartarefa.CriarTarefaViewModel
import com.agenda.up.viewmodel.criartarefa.CriarTarefaViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class FragmentCriarTarefa : Fragment(R.layout.frag_layout_criar_tarefa) {


    private lateinit var binding: FragLayoutCriarTarefaBinding
    private lateinit var viewModel: CriarTarefaViewModel
    private lateinit var calendar: Calendar
    private lateinit var timePickerDialog: TimePickerDialog


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragLayoutCriarTarefaBinding.bind(view)



        viewModel = ViewModelProvider(
            this,
            CriarTarefaViewModelFactory(CriarTarefaRepositorio())
        )[CriarTarefaViewModel::class.java]

        val ano = binding.txtAno
        val mes = binding.txtMes
        val dia = binding.txtDia
        val hr = binding.txtHrs
        val mi = binding.txtMin
        val editDescricao = binding.editDescricao
        val editDetalhes = binding.editDetalhes
        val grupoRadioPrioridades = binding.prioridades
        val checkDescricao = binding.checkBox
        val checkDetalhes = binding.checkBox2
        val checkprioridades = binding.checkBox3

        setDataHorasAtuais(dia, hr, mi, ano, mes)

        binding.viewDia.setOnClickListener {
            DatePickerFragment { year, month, dayofmonth ->
                ano.text = year; mes.text = month; dia.text = dayofmonth
            }
                .show(childFragmentManager, "DatePicker")
        }


        checkDescricaoDetalhesEtiquetas(
            editDescricao,
            editDetalhes,
            checkDescricao,
            checkDetalhes,
            checkprioridades,
            grupoRadioPrioridades
        )


        binding.viewHora.setOnClickListener {
            val txtHrs = binding.txtHrs
            val txtMin = binding.txtMin
            mostrarHoras(txtHrs, txtMin, requireContext())
        }

        binding.btSalvar.setOnClickListener {
            val anoFinal = binding.txtAno.text.toString()
            val mesFinal = binding.txtMes.text.toString()
            val diaFinal = binding.txtDia.text.toString()
            val txtHrsFinal = binding.txtHrs.text.toString()
            val txtMinFinal = binding.txtMin.text.toString()
            val editTituloFinal = binding.editTitulo.text.toString()
            val editDescricaoFinal = binding.editDescricao.text.toString()
            val editDetalhesFinal = binding.editDetalhes.text.toString()
            val checkLembrete = binding.checkBox4
            val prioridade1 = binding.btRadioSuave
            val prioridade2 = binding.btRadioNorapido
            val prioridade3 = binding.btRadioDuque
            val diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            val prioridadeSalva = checkDePrioridade(prioridade1, prioridade2, prioridade3)

            if (editTituloFinal.isEmpty()) {
                binding.editTitulo.error = "Titulo obrigatorio!"
            } else {
                ativarAlarme(txtHrsFinal, txtMinFinal, editTituloFinal,checkLembrete,diaFinal,diaAtual)
                if ((checkLembrete.isChecked && diaFinal == diaAtual.toString()) || (!checkLembrete.isChecked && diaFinal != diaAtual.toString() ) || (!checkLembrete.isChecked && diaFinal == diaAtual.toString())) {
                    viewModel.salvarTarefa(
                        anoFinal,
                        mesFinal,
                        diaFinal,
                        txtHrsFinal,
                        txtMinFinal,
                        editTituloFinal,
                        editDescricaoFinal,
                        editDetalhesFinal,
                        prioridadeSalva,
                        requireContext()
                    )
                    requireActivity().finish()
                }
            }
        }
    }

    private fun alertDialogErroLembrete() {

        val builder = AlertDialog.Builder(requireContext())
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

    private fun setDataHorasAtuais(
        txtDia: TextView,
        txtHrs: TextView,
        txtMin: TextView,
        txtAno: TextView,
        txtMes: TextView
    ) {
        val c = Calendar.getInstance()
        val anoAtual = c.get(Calendar.YEAR)
        val mesMenos1 = c.get(Calendar.MONTH)
        val diaAtual = c.get(Calendar.DAY_OF_MONTH)
        val hrAtual = c.get(Calendar.HOUR_OF_DAY)
        val miAtual = c.get(Calendar.MINUTE)
        val mesAtual = mesMenos1 + 1

        txtAno.text = anoAtual.toString()
        txtMes.text = mesAtual.toString()
        txtDia.text = diaAtual.toString()
        txtHrs.text = hrAtual.toString()
        txtMin.text = miAtual.toString()
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

    private fun mostrarHoras(txtHrs: TextView, txtMin: TextView, context: Context) {

        calendar = Calendar.getInstance()
        val horaAtual = calendar.get(Calendar.HOUR_OF_DAY)
        val minutosAtuais = calendar.get(Calendar.MINUTE)

        timePickerDialog = TimePickerDialog(
            context,
            { _: TimePicker, hrs: Int, min: Int ->
                txtHrs.text = String.format("%02d", hrs)
                txtMin.text = String.format("%02d", min)
            },
            horaAtual, minutosAtuais, true,
        )
        timePickerDialog.show()
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

    private fun checkDescricaoDetalhesEtiquetas(
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
}