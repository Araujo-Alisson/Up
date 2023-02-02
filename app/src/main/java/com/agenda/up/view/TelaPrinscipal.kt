package com.agenda.up.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.up.R
import com.agenda.up.adapter.AdapterRecyclerTarefa
import com.agenda.up.databinding.ActivityTelaPrinscipalBinding
import com.agenda.up.fragments.FragTarefas
import com.agenda.up.fragments.FragmentCalendario
import com.agenda.up.fragments.FragmentCriarTarefa
import com.agenda.up.repositorio.PrincipalRepositorio
import com.agenda.up.room.EntidadeRoom
import com.agenda.up.viewmodel.principal.PrincipalViewModel
import com.agenda.up.viewmodel.principal.PrincipalViewModelFactory

class TelaPrinscipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrinscipalBinding
    private lateinit var viewModel: PrincipalViewModel
    private lateinit var adapterTarefa: AdapterRecyclerTarefa
    private var listaTarefasFinal: MutableList<EntidadeRoom> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrinscipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            PrincipalViewModelFactory(PrincipalRepositorio())
        )[PrincipalViewModel::class.java]

        replaceFrags(FragmentCalendario())
        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        viewModel.reculperarTarefas(this)


        val atualizarEdicao: Boolean = intent.extras?.getBoolean("atualizarTelaEdit") == false
        if (atualizarEdicao) {
            adapterTarefa.notifyDataSetChanged()
        }


        supportFragmentManager.setFragmentResultListener("ano", this) { resultkey, bundle ->
            if (resultkey == "ano") {
                listaTarefasFinal.clear()
                val anoC = bundle.getString("ano").toString()
                val mesC = bundle.getString("mes").toString()
                val diaC = bundle.getString("dia").toString()

                viewModel.lisTtarefasO.observe(this) { dbTarefas ->
                    viewModel.filtroListaTarefas(dbTarefas, anoC, mesC, diaC)
                }
            }
        }
        viewModel.tarefasFiltradasO.observe(this) { listaFiltrada ->
            listaTarefasFinal = listaFiltrada
            listaTarefasFinal.sortWith(compareBy { it.hrs })

            animacao()

            val recycler = binding.recycler
            recycler.layoutManager = LinearLayoutManager(this)
            adapterTarefa = AdapterRecyclerTarefa(this, listaTarefasFinal)
            recycler.hasFixedSize()
            recycler.adapter = adapterTarefa
        }
        binding.bottomManu.setOnItemSelectedListener {
            navegarEntreTelas(it)
        }
    }

    private fun animacao() {
        if (listaTarefasFinal.isEmpty()) {
            binding.animacao.visibility = View.VISIBLE

        } else {
            binding.animacao.visibility = View.GONE
        }
    }


    private fun navegarEntreTelas(it: MenuItem): Boolean {

        when (it.itemId) {
            R.id.bottom_calendario -> {
                binding.recycler.visibility = View.VISIBLE
                replaceFrags(FragmentCalendario())
            }
            R.id.bottom_criar -> {
                binding.recycler.visibility = View.VISIBLE
                replaceFrags(FragmentCriarTarefa())
            }
            R.id.bottom_tarefas -> {
                binding.recycler.visibility = View.GONE
                replaceFrags(FragTarefas())
            }
            else -> {
            }
        }
        return true
    }

    private fun replaceFrags(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transactionFragment = fragmentManager.beginTransaction()
        transactionFragment.replace(R.id.fragment_conteiner, fragment)
        transactionFragment.commit()
    }


}