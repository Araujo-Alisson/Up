package com.agenda.up.room

import android.annotation.SuppressLint
import android.content.Context
import com.agenda.up.adapter.AdapterRecyclerTarefa
import com.agenda.up.listeners.telaprincipal.ReculperarTarefas
import com.agenda.up.view.TelaPrinscipal
import com.agenda.up.view.modeltarefa.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetEndSet {

    private val listaTarefas: MutableList<EntidadeRoom> = mutableListOf()

    fun reculperarTarefa(context: Context, listener: ReculperarTarefas){
        CoroutineScope(Dispatchers.IO).launch {
        val tarefaDao = AppDatabase.getInstance(context).tarefasDao()
        val listaTarefas: MutableList<EntidadeRoom> = tarefaDao.reculperarTarefa()
        listener.onSucesso(listaTarefas)
        }
    }
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
        context: Context,
    ){
        CoroutineScope(Dispatchers.IO).launch {
            val tarefa = EntidadeRoom(
                ano, mes, dia, txtHrs.toInt(), txtMin.toInt(), editTitulo,
                editDescricao1, editDetalhes1,prioridadeSalva
            )
            val dao = AppDatabase.getInstance(context).tarefasDao()
            listaTarefas.add(tarefa)
            dao.inserir(listaTarefas)
        }
    }

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
        editarTarefa: Context,
        prioridadeSalva: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch{
            val dao = AppDatabase.getInstance(editarTarefa).tarefasDao()
            dao.atualizar(uid,ano,mes,dia,hrs,min,titulo,descricao,detalhes,prioridadeSalva)
        }
    }

    fun deletarItem(context: Context,uid: Int){
        CoroutineScope(Dispatchers.IO).launch {

            val tarefaDao = AppDatabase.getInstance(context).tarefasDao()
            tarefaDao.deletar(uid)
        }
    }

}