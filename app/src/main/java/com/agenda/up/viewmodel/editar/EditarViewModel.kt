package com.agenda.up.viewmodel.editar

import android.content.Context
import androidx.lifecycle.ViewModel
import com.agenda.up.repositorio.EditarRepositorio

class EditarViewModel (private val repositorio:EditarRepositorio): ViewModel() {
    fun atualizarTarefa(
        uid: Int,
        ano: String,
        mes: String,
        dia: String,
        hrs: Int,
        min: Int,
        titulo: String,
        descricao: String,
        detalhes: String,
        prioridadeSalva: Int,
        editarTarefa: Context
    ) {
        repositorio.atualizarTareva(uid,ano,mes,dia,hrs,min,titulo,descricao,detalhes,editarTarefa,prioridadeSalva)
    }

}