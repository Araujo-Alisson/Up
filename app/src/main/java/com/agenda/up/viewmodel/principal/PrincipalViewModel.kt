package com.agenda.up.viewmodel.principal

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.up.listeners.telaprincipal.ReculperarTarefas
import com.agenda.up.repositorio.PrincipalRepositorio
import com.agenda.up.room.EntidadeRoom
import kotlin.math.log

class PrincipalViewModel(private val repositorio: PrincipalRepositorio): ViewModel() {

    private val lisTtarefas = MutableLiveData<MutableList<EntidadeRoom>>()
    val lisTtarefasO: MutableLiveData<MutableList<EntidadeRoom>> = lisTtarefas

    private val tarefasFiltradas = MutableLiveData<MutableList<EntidadeRoom>>()
    val tarefasFiltradasO: MutableLiveData<MutableList<EntidadeRoom>> = tarefasFiltradas



    fun reculperarTarefas(prinscipal: Context) {
        repositorio.reculperarTarefas(prinscipal, object : ReculperarTarefas{
            override fun onSucesso(tarefas: MutableList<EntidadeRoom>) {

                lisTtarefas.postValue(tarefas)
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }

        })
    }

     fun filtroListaTarefas(
        it: MutableList<EntidadeRoom>?,
        anoC: String,
        mesC: String,
        diaC: String
    ) {
         val listaTarefas: MutableList<EntidadeRoom> = mutableListOf()
         if (it != null) {
             for (count in 0 until it.size) {
                 Log.d("alissonnn", "ano")
                 Log.d("alissonnn", it[count].ano.toString())
                 Log.d("alissonnn", "mes")
                 Log.d("alissonnn", it[count].mes.toString())
                 Log.d("alissonnn", "dia")
                 Log.d("alissonnn", it[count].dia.toString())
                 if (it[count].ano == anoC  && it[count].mes == mesC && it[count].dia == diaC){

                     val tarefa = EntidadeRoom(it[count].ano, it[count].mes, it[count].dia, it[count].hrs,
                         it[count].min, it[count].titulo, it[count].descricao, it[count].detalhes,it[count].prioridadeSalva,it[count].uid)

                     listaTarefas.add(tarefa)
                 }
             }
             tarefasFiltradas.postValue(listaTarefas)
         }
    }
}