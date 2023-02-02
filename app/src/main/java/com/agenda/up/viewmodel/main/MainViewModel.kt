package com.agenda.up.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.up.listeners.telamain.Login
import com.agenda.up.listeners.telamain.VerificarCurrentUser
import com.agenda.up.repositorio.MainRepositorio

class MainViewModel(private val repositorio: MainRepositorio): ViewModel() {

    private val login = MutableLiveData<Boolean>()
    val loginO: LiveData<Boolean> = login

    private val msg = MutableLiveData<String>()
    val msgO: LiveData<String> = msg

    private val statusUsuario = MutableLiveData<Boolean>()
    val statusUsuarioO: LiveData<Boolean> = statusUsuario


    fun autenticacaoLogin(email: String,senha: String){
        if(email.isNotEmpty() || senha.isNotEmpty()){
            repositorio.autenticacaoLogin(email, senha, object : Login {
                override fun onSucesso() {
                    login.postValue(true)
                    msg.postValue("Entrou")
                }
                override fun onErro(mensagem: String) {
                    login.postValue(false)
                    msg.postValue(mensagem)
                }
            })
        }else{
            login.postValue(false)
            msg.postValue("preencha todos os campos")
        }
    }

    fun currentUser() {
        repositorio.currentUser(object : VerificarCurrentUser {
            override fun onSucesso() {
                login.postValue(true)
            }

            override fun onFailure() {
                login.postValue(false)
            }

        })
    }
}