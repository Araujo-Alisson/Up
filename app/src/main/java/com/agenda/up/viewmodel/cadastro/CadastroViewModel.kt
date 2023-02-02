package com.agenda.up.viewmodel.cadastro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.up.listeners.telacadastro.ValidacaoCadastro
import com.agenda.up.repositorio.CadastroRepositorio
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import java.lang.Exception

class CadastroViewModel (private val repositorio: CadastroRepositorio): ViewModel() {

    private val cadastro = MutableLiveData<Boolean>()
    val cadastroO: LiveData<Boolean> = cadastro


    private val msgResp = MutableLiveData<String>()
    val msgO: LiveData<String> = msgResp

    fun validacaoDeCadastro(email:String, senha:String) {
        if (email.isEmpty() || senha.isEmpty()){

            msgResp.postValue("preencha todos os campos")

        }else{
            Log.d("alison", "viewModel")
            repositorio.validacaoDeCadastro(email,senha, object : ValidacaoCadastro{
                override fun onSuccess() {

                    cadastro.postValue(true)
                }
                override fun onFailure(erro: Exception) {
                    cadastro.postValue(false)
                        val msg = when(erro){
                        is FirebaseAuthUserCollisionException -> "Email ja cadastrado"
                        is FirebaseAuthWeakPasswordException -> "Senha precisa ter no minimo 6 caracteres"
                        is FirebaseNetworkException -> "Sem rede"
                        else -> "Falha ao cadastrar"
                    }
                    Log.d("alison", msg)
                    msgResp.postValue(msg)
                }

            })
        }
    }


}