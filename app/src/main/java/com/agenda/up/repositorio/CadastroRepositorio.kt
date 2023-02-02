package com.agenda.up.repositorio

import com.agenda.up.firebase.FirebaseAuth
import com.agenda.up.listeners.telacadastro.ValidacaoCadastro

class CadastroRepositorio {
    private val auth = FirebaseAuth()
    fun validacaoDeCadastro(email: String, senha: String, listener: ValidacaoCadastro) {
        auth.validacaoCadastro(email,senha, listener )
    }

}