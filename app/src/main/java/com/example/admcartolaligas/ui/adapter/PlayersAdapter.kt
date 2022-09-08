package com.example.admcartolaligas.ui.adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admcartolaligas.databinding.ItemAdapterBinding
import com.example.admcartolaligas.model.Players

class PlayersAdapter(



    private val playerList: List<Players>,
    val taskSelected: (Players, Int) -> Unit



) : RecyclerView.Adapter<PlayersAdapter.MyViewHolder>() {


    companion object{
        val SELECT_EDIT: Int = 1
        val SELECT_REMOVE: Int = 2
        val SELECT_DETAILS: Int = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Exibir as informações de cada tarefa
        val players = playerList[position]

        holder.binding.txtNomeTime.text = players.nameClub
        holder.binding.txtNomeJogador.text = players.namePlayer
        holder.binding.txtPontosTime.text = players.pontuacaoJogador.toString()


        holder.binding.btnEditar.setOnClickListener{taskSelected(players, SELECT_EDIT)}
        holder.binding.btnRemover.setOnClickListener{taskSelected(players, SELECT_REMOVE )}
        holder.binding.cardViewFormPlayers.setOnClickListener{taskSelected(players, SELECT_DETAILS)}

        
        //expandCardView(holder)

    }


    override fun getItemCount() = playerList.size

    // EXPANDE O CARD
    private fun expandCardView(holder: MyViewHolder){
        val expansivel = holder.binding
        expansivel.llCardViewFormPlayers.setOnClickListener{
            if(expansivel.llExpandable.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(holder.binding.cardViewFormPlayers, AutoTransition())
                expansivel.llExpandable.visibility = View.VISIBLE
            } else{
                TransitionManager.beginDelayedTransition(holder.binding.cardViewFormPlayers, AutoTransition())
                expansivel.llExpandable.visibility = View.GONE
            }
        }
    }



    // Implementação do ViewBinding
    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)


}