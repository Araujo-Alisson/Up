package com.agenda.up.listeners.telacadastro

import java.lang.Exception

interface ValidacaoCadastro {
    fun onSuccess()
    fun onFailure(erro: Exception)
}