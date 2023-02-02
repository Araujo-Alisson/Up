package com.agenda.up.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TarefasDao {
    @Insert
    fun inserir(listaTarefas: MutableList<EntidadeRoom>)

    @Query ("SELECT * FROM tabela_tarefas ORDER BY hrs ASC")
    fun reculperarTarefa():MutableList<EntidadeRoom>

    @Query("UPDATE tabela_tarefas SET ano = :novoAno, mes = :novoMes, dia = :novaDia, hrs = :novoHrs, Min = :novoMin," +
            "titulo = :novoTitulo, descricao = :novoDescricao, detalhes = :novoDetalhes,prioridadeSalva = :novoPrioridadeSalva  WHERE uid = :id")
    fun atualizar(id: Int, novoAno:String, novoMes:String, novaDia:String, novoHrs:Int, novoMin:Int,
                  novoTitulo:String, novoDescricao:String, novoDetalhes:String, novoPrioridadeSalva:Int)

    @Query("DELETE FROM tabela_tarefas WHERE uid = :id")
    fun deletar(id: Int)
}