package com.agenda.up.viewmodel.criartarefa

import android.content.Context
import androidx.lifecycle.ViewModel
import com.agenda.up.repositorio.CriarTarefaRepositorio

class CriarTarefaViewModel(private val repositorio: CriarTarefaRepositorio) : ViewModel() {

    fun salvarTarefa(
        ano: String, mes: String, dia: String, txtHrs: String, txtMin: String, editTitulo: String,
        editDescricao1: String, editDetalhes1: String, prioridadeSalva: Int, context: Context
    ) {
        repositorio.salvarTarefa(
            ano,
            mes,
            dia,
            txtHrs,
            txtMin,
            editTitulo,
            editDescricao1,
            editDetalhes1,
            prioridadeSalva,
            context
        )
    }
}