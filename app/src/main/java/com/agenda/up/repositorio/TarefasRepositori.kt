package com.agenda.up.repositorio

import android.content.Context
import com.agenda.up.listeners.telaprincipal.ReculperarTarefas
import com.agenda.up.room.GetEndSet

class TarefasRepositori {

    val getendSet = GetEndSet()

    fun reculperarTarefas(tarefas: Context, listener: ReculperarTarefas) {
        getendSet.reculperarTarefa(tarefas, listener)
    }

}