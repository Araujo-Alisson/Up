package com.agenda.up.repositorio

import android.content.Context
import com.agenda.up.room.GetEndSet

class CriarTarefaRepositorio {

    private val getendSet = GetEndSet()

    fun salvarTarefa(
        ano: String,
        mes: String,
        dia: String,
        txtHrs: String,
        txtMin: String,
        editTitulo: String,
        editDescricao1: String,
        editDetalhes1: String,
        prioridadeSalva: Int,
        context: Context
    ) {
        getendSet.salvarTarefa(ano,mes,dia,txtHrs,txtMin,editTitulo,editDescricao1,editDetalhes1,prioridadeSalva,context)
    }

}