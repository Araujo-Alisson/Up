package com.agenda.up.repositorio

import com.agenda.up.firebase.FirebaseAuth
import com.agenda.up.listeners.telamain.Login
import com.agenda.up.listeners.telamain.VerificarCurrentUser

class MainRepositorio {

    private  var firebaseAuth = FirebaseAuth()

    fun autenticacaoLogin(email:String, senha:String, listener: Login){
        firebaseAuth.autenticacaoLogin(email,senha, listener)
    }
    fun currentUser(resp: VerificarCurrentUser) {
        firebaseAuth.verificarCurrentUser(resp)
    }
}