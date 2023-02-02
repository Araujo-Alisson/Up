package com.agenda.up.view.modeltarefa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agenda.up.room.EntidadeRoom
import com.agenda.up.room.TarefasDao

@Database(entities = [EntidadeRoom::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun tarefasDao(): TarefasDao


    companion object{
        private const val DATABASE_NOME = "DB_TAREFAS"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NOME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}