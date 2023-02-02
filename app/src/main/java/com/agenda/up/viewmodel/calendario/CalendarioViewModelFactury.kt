package com.agenda.up.viewmodel.calendario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.repositorio.CalendarioRepositorio

class CalendarioViewModelFactury(private val repositorio: CalendarioRepositorio):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalendarioViewModel(repositorio) as T
    }
}