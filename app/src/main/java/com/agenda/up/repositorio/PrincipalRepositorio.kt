package com.agenda.up.repositorio

import android.content.Context
import com.agenda.up.listeners.telaprincipal.ReculperarTarefas
import com.agenda.up.room.GetEndSet

class PrincipalRepositorio {

    private val getendSet = GetEndSet()

    fun reculperarTarefas(prinscipal: Context, listener: ReculperarTarefas) {
        getendSet.reculperarTarefa(prinscipal, listener)
    }

}
