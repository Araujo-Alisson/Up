package com.agenda.up.viewmodel.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.CadastroRepositorio

class CadastroViewMoldelFactory(private val repositer:CadastroRepositorio):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CadastroViewModel(repositer) as T
    }
}