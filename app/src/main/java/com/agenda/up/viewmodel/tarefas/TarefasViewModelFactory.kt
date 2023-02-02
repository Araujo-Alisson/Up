package com.agenda.up.viewmodel.tarefas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.TarefasRepositori

class TarefasViewModelFactory(private val repositori: TarefasRepositori):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TarefasViewModel(repositori) as T
    }
}