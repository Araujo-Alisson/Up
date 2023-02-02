package com.agenda.up.viewmodel.tarefas

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.up.listeners.telaprincipal.ReculperarTarefas
import com.agenda.up.repositorio.TarefasRepositori
import com.agenda.up.room.EntidadeRoom

class TarefasViewModel(private val repositorio: TarefasRepositori) : ViewModel() {

    val lisTtarefas = MutableLiveData<MutableList<EntidadeRoom>>()
    val lisTtarefasO: MutableLiveData<MutableList<EntidadeRoom>> = lisTtarefas

    fun reculperarTarefas(tarefas: Context) {
        repositorio.reculperarTarefas(tarefas, object : ReculperarTarefas {
            override fun onSucesso(tarefas: MutableList<EntidadeRoom>) {

                lisTtarefas.postValue(tarefas)
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }

        })
    }
}
