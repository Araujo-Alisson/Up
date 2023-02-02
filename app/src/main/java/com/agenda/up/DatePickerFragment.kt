package com.agenda.up

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment(val callback: (year:String,month:String,dayofmonth:String) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONDAY)
        val dia = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(),this,ano,mes,dia)
    }

    override fun onDateSet(p0: DatePicker?, ano: Int, mes: Int, dia: Int) {

        val anoString = ano.toString()
        val mesString = (mes + 1).toString()
        val diaString = dia.toString()
        callback(anoString,mesString, diaString)
    }
}