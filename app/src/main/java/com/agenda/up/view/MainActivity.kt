package com.agenda.up.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.databinding.ActivityMainBinding
import com.agenda.up.repositorio.MainRepositorio
import com.agenda.up.viewmodel.main.MainViewModel
import com.agenda.up.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MainRepositorio())
        )[MainViewModel::class.java]

        binding.btLogar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            viewModel.autenticacaoLogin(email, senha)
        }


        binding.btCadastrar.setOnClickListener {
            irParaTelaCadastro()
        }
    }

    private fun irParaTelaPrinsipal() {
        val intent = Intent(this,TelaPrinscipal::class.java)
        startActivity(intent)
    }


    private fun irParaTelaCadastro() {
        val intent = Intent(this, FormCadastro::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.currentUser()
        viewModel.loginO.observe(this){ resp ->

                if (resp) {
                    irParaTelaPrinsipal()
                }else{
                    viewModel.msgO.observe(this) { msg ->
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



