package com.agenda.up.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.databinding.ActivityFormCadastroBinding
import com.agenda.up.repositorio.CadastroRepositorio
import com.agenda.up.viewmodel.cadastro.CadastroViewModel
import com.agenda.up.viewmodel.cadastro.CadastroViewMoldelFactory

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    private lateinit var viewMoldel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.BLACK

        viewMoldel = ViewModelProvider(
            this,
            CadastroViewMoldelFactory(CadastroRepositorio())
        )[CadastroViewModel::class.java]

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            viewMoldel.validacaoDeCadastro(email, senha)

            viewMoldel.cadastroO.observe(this){ resp ->
                    if (resp) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        viewMoldel.msgO.observe(this) {msg ->
                        Toast.makeText(this,msg , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}