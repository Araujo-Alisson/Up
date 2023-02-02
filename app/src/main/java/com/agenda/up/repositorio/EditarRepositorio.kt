package com.agenda.up.repositorio

import android.content.Context
import com.agenda.up.room.GetEndSet

class EditarRepositorio {

    private val getendSet = GetEndSet()

    fun atualizarTareva(
        uid: Int,
        ano: String,
        mes: String,
        dia: String,
        hrs: Int,
        min: Int,
        titulo: String,
        descricao: String,
        detalhes: String,
        editarTarefa: Context,
        prioridadeSalva: Int
    ) {
        getendSet.atualizarTarefa(uid,ano,mes,dia,hrs,min,titulo,descricao,detalhes,editarTarefa,prioridadeSalva)
    }
}