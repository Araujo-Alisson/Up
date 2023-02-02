package com.agenda.up.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.up.R
import com.agenda.up.adapter.AdapterRecyclerTarefa
import com.agenda.up.databinding.FragLayoutTarefasBinding
import com.agenda.up.repositorio.TarefasRepositori
import com.agenda.up.room.EntidadeRoom
import com.agenda.up.viewmodel.tarefas.TarefasViewModel
import com.agenda.up.viewmodel.tarefas.TarefasViewModelFactory

class FragTarefas : Fragment(R.layout.frag_layout_tarefas) {

    private lateinit var binding: FragLayoutTarefasBinding
    private lateinit var adapterTarefa: AdapterRecyclerTarefa
    private lateinit var viewModel: TarefasViewModel
    private var listaTarefasFinal: MutableList<EntidadeRoom> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragLayoutTarefasBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            TarefasViewModelFactory(TarefasRepositori())
        )[TarefasViewModel::class.java]

        viewModel.reculperarTarefas(requireContext())

        viewModel.lisTtarefasO.observe(requireActivity()) { listaTarefas ->
            listaTarefasFinal = listaTarefas
            listaTarefas.sortWith(compareBy { it.prioridadeSalva })
            val recycler = binding.recyclerFragTarefas
            recycler.layoutManager = LinearLayoutManager(requireContext())
            adapterTarefa = AdapterRecyclerTarefa(requireContext(), listaTarefasFinal)
            recycler.hasFixedSize()
            recycler.adapter = adapterTarefa
            adapterTarefa.notifyDataSetChanged()
        }
    }
}