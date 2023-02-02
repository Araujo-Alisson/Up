package com.agenda.up.listeners.telaprincipal

import com.agenda.up.room.EntidadeRoom

interface ReculperarTarefas {

    fun onSucesso(tarefas: MutableList<EntidadeRoom>)
    fun onFailure(msg:String)
}