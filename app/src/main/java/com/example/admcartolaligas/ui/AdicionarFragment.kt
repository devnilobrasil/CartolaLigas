package com.example.admcartolaligas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.CustomDialogBinding
import com.example.admcartolaligas.databinding.FragmentAdicionarBinding
import com.example.admcartolaligas.helper.FirebaseHelper
import com.example.admcartolaligas.helper.showBottomSheet
import com.example.admcartolaligas.model.Players
import com.example.admcartolaligas.ui.adapter.PlayersAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AdicionarFragment : Fragment() {

    private var _binding: FragmentAdicionarBinding? = null
    private val binding get() = _binding!!
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var alertDialog: AlertDialog

    private val playerList = mutableListOf<Players>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdicionarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()
        getPlayers()

    }

    private fun initClicks() {
        binding.fabAddPlayers.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToFormPlayerFragment2(null)
            findNavController().navigate(action)
        }
    }

    // LER AS INFORMAÇÕES DO BANCO DE DADOS E ARMAZENA NO APP
    private fun getPlayers() {
        FirebaseHelper.getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        playerList.clear()
                        for (snap in snapshot.children) {
                            val players = snap.getValue(Players::class.java) as Players
                            if (players.status == 0) {
                                playerList.add(players)
                            }
                        }

                        binding.txtInfoAdicionar.text = ""
                        initAdapter()
                    } else {
                        binding.txtInfoAdicionar.text = getString(R.string.txt_nenhum_player)
                    }
                    binding.progressBar.isVisible = false
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Erro. Tente novamente mais tarde!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun initAdapter() {
        binding.rvAdicionar.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdicionar.setHasFixedSize(true)
        playersAdapter = PlayersAdapter(playerList) { task, select ->
            optionSelect(task, select)
        }
        binding.rvAdicionar.adapter = playersAdapter
    }

    private fun optionSelect(players: Players, select: Int) {
        when (select) {
            PlayersAdapter.SELECT_REMOVE -> {

                showBottomSheet(
                    message = R.string.text_remove_bottom_sheet,
                    titleButton = R.string.bs_button_confirm,
                    titleDialog = R.string.text_title_remove_bottom_sheet,
                    onClick = { removePlayer(players) }
                )

            }

            PlayersAdapter.SELECT_EDIT -> {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToFormPlayerFragment2(players)
                findNavController().navigate(action)
            }

            PlayersAdapter.SELECT_DETAILS -> {
                val builder = AlertDialog.Builder(requireContext(), R.style.ThemeCustomDialog)
                val dialogBinding: CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)


                dialogBinding.txtNomeTime.text = players.nameClub
                dialogBinding.txtNomeJogador.text = players.namePlayer
                dialogBinding.txtPontosTime.text = players.pontuacaoJogador.toString()

                dialogBinding.btnClickConfirm.setOnClickListener { alertDialog.dismiss() }

                visibilityIconTitles(players, dialogBinding)

                builder.setView(dialogBinding.root)
                alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }


    private fun visibilityIconTitles(players: Players, dialogBinding: CustomDialogBinding) {
        //Geral
        val arrayGeral = mutableListOf<String>()

        FirebaseHelper
            .getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .child("qtdeTitulos")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        arrayGeral.add(snap.key.toString())

                        if (arrayGeral.contains("Campeão Geral")) {
                            dialogBinding.imgCGeral.visibility = View.VISIBLE
                            dialogBinding.txtCGeral.visibility = View.VISIBLE
                            if (snap.key == "Campeão Geral") dialogBinding.txtCGeral.text =
                                "(${snap.value.toString()})"


                        } else {
                            dialogBinding.imgCGeral.visibility = View.GONE
                            dialogBinding.txtCGeral.visibility = View.GONE
                        }
                        if (arrayGeral.contains("Vice-Geral")) {
                            dialogBinding.imgVGeral.visibility = View.VISIBLE
                            dialogBinding.txtVGeral.visibility = View.VISIBLE
                            if (snap.key == "Vice-Geral") dialogBinding.txtVGeral.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgVGeral.visibility = View.GONE
                            dialogBinding.txtVGeral.visibility = View.GONE
                        }
                        if (arrayGeral.contains("3º Geral")) {
                            dialogBinding.imgTGeral.visibility = View.VISIBLE
                            dialogBinding.txtTGeral.visibility = View.VISIBLE
                            if (snap.key == "3º Geral") dialogBinding.txtTGeral.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgTGeral.visibility = View.GONE
                            dialogBinding.txtTGeral.visibility = View.GONE
                        }
                        if (arrayGeral.contains("4º Geral")) {
                            dialogBinding.imgQuartoGeral.visibility = View.VISIBLE
                            dialogBinding.txtQGeral.visibility = View.VISIBLE
                            if (snap.key == "4º Geral") dialogBinding.txtQGeral.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgQuartoGeral.visibility = View.GONE
                            dialogBinding.txtQGeral.visibility = View.GONE
                        }
                        if (arrayGeral.contains("5º Geral")) {
                            dialogBinding.imgQuintoGeral.visibility = View.VISIBLE
                            dialogBinding.txtQuintoGeral.visibility = View.VISIBLE
                            if (snap.key == "5º Geral") dialogBinding.txtQuintoGeral.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgQuintoGeral.visibility = View.GONE
                            dialogBinding.txtQuintoGeral.visibility = View.GONE
                        }
                        if ("Campeão Geral" in arrayGeral
                            || "Vice-Geral" in arrayGeral
                            || "3º Geral" in arrayGeral
                            || "4º Geral" in arrayGeral
                            || "5º Geral" in arrayGeral
                        ) {
                            dialogBinding.llTitulosGeral.visibility = View.VISIBLE
                            dialogBinding.labelGeral.visibility = View.VISIBLE
                        } else {
                            dialogBinding.llTitulosGeral.visibility = View.GONE
                            dialogBinding.labelGeral.visibility = View.GONE
                        }

                        // MÊS

                        if (arrayGeral.contains("Campeão do Mês")) {
                            dialogBinding.imgCMes.visibility = View.VISIBLE
                            dialogBinding.txtCMes.visibility = View.VISIBLE
                            if (snap.key == "Campeão do Mês") dialogBinding.txtCMes.text =
                                "(${snap.value.toString()})"

                        } else {
                            dialogBinding.imgCMes.visibility = View.GONE
                            dialogBinding.txtCMes.visibility = View.GONE
                        }
                        if (arrayGeral.contains("Vice do Mês")) {
                            dialogBinding.imgVMes.visibility = View.VISIBLE
                            dialogBinding.txtVMes.visibility = View.VISIBLE
                            if (snap.key == "Vice do Mês") dialogBinding.txtVMes.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgVMes.visibility = View.GONE
                            dialogBinding.txtVMes.visibility = View.GONE
                        }
                        if (arrayGeral.contains("3º do Mês")) {
                            dialogBinding.imgTMes.visibility = View.VISIBLE
                            dialogBinding.txtTMes.visibility = View.VISIBLE
                            if (snap.key == "3º do Mês") dialogBinding.txtTMes.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgTMes.visibility = View.GONE
                            dialogBinding.txtTMes.visibility = View.GONE
                        }
                        if ("Campeão do Mês" in arrayGeral
                            || "Vice do Mês" in arrayGeral
                            || "3º do Mês" in arrayGeral
                        ) {
                            dialogBinding.llTitulosMes.visibility = View.VISIBLE
                            dialogBinding.labelMes.visibility = View.VISIBLE
                        } else {
                            dialogBinding.llTitulosMes.visibility = View.GONE
                            dialogBinding.labelMes.visibility = View.GONE
                        }

                        // COPA

                        if (arrayGeral.contains("Campeão da Copa")) {
                            dialogBinding.imgCCopa.visibility = View.VISIBLE
                            dialogBinding.txtCCopa.visibility = View.VISIBLE
                            if (snap.key == "Campeão da Copa") dialogBinding.txtCCopa.text =
                                "(${snap.value.toString()})"

                        } else {
                            dialogBinding.imgCCopa.visibility = View.GONE
                            dialogBinding.txtCCopa.visibility = View.GONE
                        }
                        if (arrayGeral.contains("Vice da Copa")) {
                            dialogBinding.imgVCopa.visibility = View.VISIBLE
                            dialogBinding.txtVCopa.visibility = View.VISIBLE
                            if (snap.key == "Vice da Copa") dialogBinding.txtVCopa.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgVCopa.visibility = View.GONE
                            dialogBinding.txtVCopa.visibility = View.GONE
                        }
                        if (arrayGeral.contains("3º da Copa")) {
                            dialogBinding.imgTCopa.visibility = View.VISIBLE
                            dialogBinding.txtTCopa.visibility = View.VISIBLE
                            if (snap.key == "3º da Copa") dialogBinding.txtTCopa.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgTCopa.visibility = View.GONE
                            dialogBinding.txtTCopa.visibility = View.GONE
                        }
                        if ("Campeão da Copa" in arrayGeral
                            || "Vice da Copa" in arrayGeral
                            || "3º da Copa" in arrayGeral
                        ) {
                            dialogBinding.llTitulosCopa.visibility = View.VISIBLE
                            dialogBinding.labelCopa.visibility = View.VISIBLE
                        } else {
                            dialogBinding.llTitulosCopa.visibility = View.GONE
                            dialogBinding.labelCopa.visibility = View.GONE
                        }

                        // TURNO

                        if (arrayGeral.contains("Campeão do Turno")) {
                            dialogBinding.imgCTurno.visibility = View.VISIBLE
                            dialogBinding.txtCTurno.visibility = View.VISIBLE
                            if (snap.key == "Campeão do Turno") dialogBinding.txtCTurno.text =
                                "(${snap.value.toString()})"
                        } else {
                            dialogBinding.imgCTurno.visibility = View.GONE
                            dialogBinding.txtCTurno.visibility = View.GONE
                        }
                        if ("Campeão do Turno" in arrayGeral) {
                            dialogBinding.llTitulosTurno.visibility = View.VISIBLE
                            dialogBinding.labelTurno.visibility = View.VISIBLE
                        } else {
                            dialogBinding.llTitulosTurno.visibility = View.GONE
                            dialogBinding.labelTurno.visibility = View.GONE
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        Log.i("Database2", "-----")
    }

    private fun visibilityIconViceGeral(players: Players, dialogBinding: CustomDialogBinding) {
        //Geral
        FirebaseHelper
            .getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .child("qtdeTitulos")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        if (snap.key == "Campeão Geral") {
                            Toast.makeText(requireContext(), "${snap.value}", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }


    // REMOVE OS JOGADORES
    private fun removePlayer(players: Players) {
        FirebaseHelper
            .getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .removeValue()

        playerList.remove(players)
        playersAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}