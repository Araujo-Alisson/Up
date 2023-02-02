package com.agenda.up.viewmodel.calendario

import androidx.lifecycle.ViewModel
import com.agenda.up.repositorio.CalendarioRepositorio

class CalendarioViewModel(private val repositorio: CalendarioRepositorio): ViewModel() {


    fun logout() {
        repositorio.logout()
    }


}