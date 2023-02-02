package com.agenda.up.firebase

import android.util.Log
import com.agenda.up.listeners.telacadastro.ValidacaoCadastro
import com.agenda.up.listeners.telamain.Login
import com.agenda.up.listeners.telamain.VerificarCurrentUser
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuth {

    private val auth = FirebaseAuth.getInstance()

     fun autenticacaoLogin(email: String, senha: String, listener: Login){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{ request ->
            if (request.isSuccessful){
                listener.onSucesso()
            }
        }.addOnFailureListener {
            listener.onErro("erro")
        }
    }

    fun verificarCurrentUser(verificarCurrentUser: VerificarCurrentUser) {
        val user = auth.currentUser
        if (user != null){
            verificarCurrentUser.onSucesso()
        }else{
            verificarCurrentUser.onFailure()
        }
    }

    fun validacaoCadastro(email:String, senha: String, listener:ValidacaoCadastro ){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener { cadastro ->
            if (cadastro.isSuccessful){
                listener.onSuccess()
                Log.d("alison", "ok")
            }
        }.addOnFailureListener {erro ->
             listener.onFailure(erro)
        }
    }

    fun logout(){
        auth.signOut()
    }

}