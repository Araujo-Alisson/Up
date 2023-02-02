package com.agenda.up.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tabela_tarefas")
data class EntidadeRoom(
    @ColumnInfo(name = "ano") val ano: String?,
    @ColumnInfo(name = "mes") val mes: String?,
    @ColumnInfo(name = "dia") val dia: String?,
    @ColumnInfo(name = "hrs") val hrs: Int,
    @ColumnInfo(name = "min") val min: Int?,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "descricao") val descricao: String?,
    @ColumnInfo(name = "detalhes") val detalhes: String?,
    @ColumnInfo(name = "prioridadeSalva") val prioridadeSalva: Int?,

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
)



