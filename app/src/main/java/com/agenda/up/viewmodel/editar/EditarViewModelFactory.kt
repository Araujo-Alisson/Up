package com.agenda.up.viewmodel.editar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.EditarRepositorio

class EditarViewModelFactory(private val repositorio: EditarRepositorio):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditarViewModel(repositorio) as T
    }
}