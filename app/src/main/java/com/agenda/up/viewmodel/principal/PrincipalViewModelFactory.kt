package com.agenda.up.viewmodel.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.PrincipalRepositorio

class PrincipalViewModelFactory(private val repositorio: PrincipalRepositorio):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PrincipalViewModel(repositorio) as T
    }
}