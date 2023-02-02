package com.agenda.up.repositorio

import com.agenda.up.firebase.FirebaseAuth

class CalendarioRepositorio {

    private val auth = FirebaseAuth()

    fun logout() {
        auth.logout()
    }


}