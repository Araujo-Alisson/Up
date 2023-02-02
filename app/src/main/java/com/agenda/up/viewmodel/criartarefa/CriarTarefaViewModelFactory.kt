package com.agenda.up.viewmodel.criartarefa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.CriarTarefaRepositorio

class CriarTarefaViewModelFactory(private val repositorio: CriarTarefaRepositorio):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CriarTarefaViewModel(repositorio) as T
    }
}