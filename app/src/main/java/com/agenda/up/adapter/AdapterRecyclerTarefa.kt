package com.agenda.up.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.agenda.up.databinding.LayoutItemRecyclerTarefaBinding
import com.agenda.up.room.EntidadeRoom
import com.agenda.up.room.GetEndSet
import com.agenda.up.view.EditarTarefa
import com.agenda.up.view.TelaPrinscipal
import java.util.Calendar
import java.util.concurrent.TimeUnit

class AdapterRecyclerTarefa(
    private val content: Context,
    private val listaTarefas: MutableList<EntidadeRoom>
) :
    RecyclerView.Adapter<AdapterRecyclerTarefa.TarefaViewHolder>() {


    inner class TarefaViewHolder(binding: LayoutItemRecyclerTarefaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val txtDescricao = binding.txtDescricao
        val txtDetalhes = binding.txtDetalhes
        var txtTitulo = binding.txtTitulo
        var horario = binding.txtHorario
        var minutos = binding.txtMinutos
        var item = binding.itemContener
        var imgEdit = binding.imgEdit
        var imgDelet = binding.imgDelet
        var linhaDesc = binding.linhaDescricao
        var linhaDet = binding.linhaDetalhes


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemTarefa =
            LayoutItemRecyclerTarefaBinding.inflate(LayoutInflater.from(content), parent, false)
        return TarefaViewHolder(itemTarefa)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {


        holder.horario.text = listaTarefas[position].hrs.toString()
        holder.minutos.text = listaTarefas[position].min.toString()
        holder.txtTitulo.text = listaTarefas[position].titulo.toString()
        holder.txtDescricao.text = listaTarefas[position].descricao.toString()
        holder.txtDetalhes.text = listaTarefas[position].detalhes.toString()




        holder.item.setOnClickListener {

            if (holder.txtDescricao.visibility == View.GONE && holder.txtDetalhes.visibility == View.GONE) {
                if (holder.txtDescricao.text.isNotEmpty() && holder.txtDetalhes.text.isNotEmpty()) {
                    holder.txtDescricao.visibility = View.VISIBLE
                    holder.txtDetalhes.visibility = View.VISIBLE
                    holder.linhaDesc.visibility = View.VISIBLE
                    holder.linhaDet.visibility = View.VISIBLE

                } else if (holder.txtDescricao.text.isNotEmpty()) {
                    holder.txtDescricao.visibility = View.VISIBLE
                    holder.linhaDesc.visibility = View.VISIBLE
                } else if (holder.txtDetalhes.text.isNotEmpty()) {
                    holder.txtDetalhes.visibility = View.VISIBLE
                    holder.linhaDet.visibility = View.VISIBLE
                }
            } else {
                holder.txtDescricao.visibility = View.GONE
                holder.txtDetalhes.visibility = View.GONE
                holder.linhaDesc.visibility = View.GONE
                holder.linhaDet.visibility = View.GONE
            }
        }



        when (listaTarefas[position].prioridadeSalva) {
            1 -> {
                holder.txtTitulo.setTextColor(Color.WHITE)
                holder.horario.setTextColor(Color.WHITE)
                holder.minutos.setTextColor(Color.WHITE)
                holder.txtDescricao.setTextColor(Color.WHITE)
                holder.txtDetalhes.setTextColor(Color.WHITE)
                holder.linhaDesc.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.linhaDet.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.item.setBackgroundColor(Color.parseColor("#8C0000"))
            }
            2 -> {
                holder.txtTitulo.setTextColor(Color.WHITE)
                holder.horario.setTextColor(Color.WHITE)
                holder.minutos.setTextColor(Color.WHITE)
                holder.txtDescricao.setTextColor(Color.WHITE)
                holder.txtDetalhes.setTextColor(Color.WHITE)
                holder.linhaDesc.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.linhaDet.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.item.setBackgroundColor(Color.parseColor("#C50000"))
            }

            3 -> {
                holder.txtTitulo.setTextColor(Color.WHITE)
                holder.horario.setTextColor(Color.WHITE)
                holder.minutos.setTextColor(Color.WHITE)
                holder.txtDescricao.setTextColor(Color.WHITE)
                holder.txtDetalhes.setTextColor(Color.WHITE)
                holder.linhaDesc.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.linhaDet.dividerColor = Color.parseColor("#FFFFFFFF")
                holder.item.setBackgroundColor(Color.parseColor("#FF3434"))
            }
        }
        holder.imgEdit.setOnClickListener {

            val intent = Intent(content, EditarTarefa::class.java)
            intent.putExtra("ano", listaTarefas[position].ano)
            intent.putExtra("mes", listaTarefas[position].mes)
            intent.putExtra("dia", listaTarefas[position].dia)
            intent.putExtra("hrs", listaTarefas[position].hrs.toString())
            intent.putExtra("min", listaTarefas[position].min.toString())
            intent.putExtra("titulo", listaTarefas[position].titulo)
            intent.putExtra("descricao", listaTarefas[position].descricao)
            intent.putExtra("detalhes", listaTarefas[position].detalhes)
            intent.putExtra("prioridade", listaTarefas[position].prioridadeSalva)
            intent.putExtra("uid", listaTarefas[position].uid)
            TelaPrinscipal().finish()
            content.startActivity(intent)
        }
        holder.imgDelet.setOnClickListener {

            val builder = AlertDialog.Builder(content)
            builder.setTitle("Deletando")
            builder.setMessage(listaTarefas[position].titulo)
            builder.setPositiveButton("OK") { _, _ ->
                val tarefa = listaTarefas[position]
                GetEndSet().deletarItem(content, tarefa.uid)
                listaTarefas.remove(tarefa)
                notifyDataSetChanged()
            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun getItemCount() = listaTarefas.size
}