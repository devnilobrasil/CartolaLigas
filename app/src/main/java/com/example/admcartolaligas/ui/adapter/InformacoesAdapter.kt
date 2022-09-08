package com.example.admcartolaligas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admcartolaligas.databinding.InformationListBinding
import com.example.admcartolaligas.model.Informacoes

class InformacoesAdapter(
    private val context: Context,
    private val listaInformacoes: MutableList<Informacoes>
) : RecyclerView.Adapter<InformacoesAdapter.InformacoesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformacoesViewHolder {
        val itemLista = InformationListBinding.inflate(LayoutInflater.from(context), parent, false)
        return InformacoesViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: InformacoesViewHolder, position: Int) {
        holder.imagemTitulo.setImageResource(listaInformacoes[position].imagem)
        holder.nomeTitulo.setText(listaInformacoes[position].nomeTitulo)
        holder.pontosTime.setText(listaInformacoes[position].pontosTime)
    }

    override fun getItemCount() = listaInformacoes.size

    inner class InformacoesViewHolder(binding: InformationListBinding) : RecyclerView.ViewHolder(binding.root){
        val imagemTitulo = binding.ivTitulo
        val nomeTitulo = binding.txtTitulo
        val pontosTime = binding.txtPontosTime
    }

}