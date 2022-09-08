package com.example.admcartolaligas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admcartolaligas.databinding.RvTitlesGeralBinding
import com.example.admcartolaligas.model.Titulos

class TitulosAdapter(
    private val context: Context,
    private val listaTitulos: MutableList<Titulos>
) : RecyclerView.Adapter<TitulosAdapter.TitulosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitulosViewHolder {
        val itemLista = RvTitlesGeralBinding.inflate(LayoutInflater.from(context), parent, false)
        return TitulosViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: TitulosViewHolder, position: Int) {
        holder.imagemTitulo.setImageResource(listaTitulos[position].imagem)
        holder.pontosTime.setText(listaTitulos[position].pontos)
    }

    override fun getItemCount() = listaTitulos.size

    class TitulosViewHolder(binding: RvTitlesGeralBinding) : RecyclerView.ViewHolder(binding.root) {
        val imagemTitulo = binding.imgTitle
        val pontosTime = binding.txtPontosTime
    }
}


