package com.agenda.up.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.agenda.up.R
import com.agenda.up.databinding.FragLayoutCalendarioBinding
import com.agenda.up.repositorio.CalendarioRepositorio
import com.agenda.up.view.MainActivity
import com.agenda.up.viewmodel.calendario.CalendarioViewModel
import com.agenda.up.viewmodel.calendario.CalendarioViewModelFactury
import java.util.*

class FragmentCalendario: Fragment(R.layout.frag_layout_calendario) {

    private lateinit var binding: FragLayoutCalendarioBinding
    private lateinit var viewModel: CalendarioViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragLayoutCalendarioBinding.bind(view)
        viewModel = ViewModelProvider(this,CalendarioViewModelFactury(CalendarioRepositorio()))[CalendarioViewModel::class.java]
    }


    override fun onResume() {
        super.onResume()

        val c: Calendar = Calendar.getInstance()
        val m = c.get(Calendar.MONTH)
        val anoC = c.get(Calendar.YEAR).toString()
        val mesC = (m + 1).toString()
        val diaC = c.get(Calendar.DAY_OF_MONTH).toString()

        val bundle = Bundle()
        bundle.putString("ano", anoC)
        bundle.putString("mes", (mesC))
        bundle.putString("dia", diaC)
        setFragmentResult("ano", bundle)


        binding.calendario.setOnDateChangeListener { _, year, month, dayofmonth ->
            bundle.putString("ano", year.toString())
            bundle.putString("mes", (month + 1).toString())
            bundle.putString("dia", dayofmonth.toString())
            setFragmentResult("ano", bundle)

        }

        binding.logout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Confirme")
            builder.setMessage("Quer mesmo deslogar o usuario?")
            builder.setPositiveButton("SIM") { _, _ ->
                viewModel.logout()
                val intent = Intent(requireContext(),MainActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}






