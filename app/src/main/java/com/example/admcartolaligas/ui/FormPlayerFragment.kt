package com.example.admcartolaligas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.BottomSheetCheckboxesBinding
import com.example.admcartolaligas.databinding.CustomDialogBinding
import com.example.admcartolaligas.databinding.FragmentFormPlayerBinding
import com.example.admcartolaligas.helper.*
import com.example.admcartolaligas.model.Players
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class FormPlayerFragment : Fragment() {

    private val args: FormPlayerFragmentArgs by navArgs()

    private var _binding: FragmentFormPlayerBinding? = null
    val binding get() = _binding!!

    private lateinit var players: Players
    private lateinit var dialogBinding: CustomDialogBinding
    private var newPlayer: Boolean = true
    private var statusTask: Int = 0

    var multiCampeaoGeral = 0
    var multiViceGeral = 0
    var multiTerceiroGeral = 0
    var multiQuartoGeral = 0
    var multiQuintoGeral = 0
    var multiCampeaoMes = 0
    var multiViceMes = 0
    var multiTerceiroMes = 0
    var multiCampeaoCopa = 0
    var multiViceCopa = 0
    var multiTerceiroCopa = 0
    var multiCampeaoTurno = 0

    var validador: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormPlayerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.tbCreatePlayer)

        initListner()

    }

    private fun initListner() {

        validateData()
        getArgs()
        titulosCheckboxes()



    }

    private fun titulosCheckboxes() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetCheckboxesBinding: BottomSheetCheckboxesBinding =
            BottomSheetCheckboxesBinding.inflate(layoutInflater, null, false)
        val numberPicker = bottomSheetCheckboxesBinding.numberPicker

        val btnIncrement = bottomSheetCheckboxesBinding.btnIncremento
        val btnDecrement = bottomSheetCheckboxesBinding.btnDecremento

        val tituloLista = arrayOf(
            binding.rdCampeaoGeral,
            binding.rdViceGeral,
            binding.rdTerceiroGeral,
            binding.rdQuartoGeral,
            binding.rdQuintoGeral,
            binding.rdCampeaoMes,
            binding.rdViceMes,
            binding.rdTerceiroMes,
            binding.rdCampeaoCopa,
            binding.rdViceCopa,
            binding.rdTerceiroCopa,
            binding.rdCampeaoTurno
        )

        for (titulos in tituloLista) {
            titulos.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked && validador) {
                    bottomSheetCheckboxesBinding.txtBsTitle.text = titulos.text.toString()

                    numberPicker.minValue = 1
                    numberPicker.maxValue = 50
                    numberPicker.wrapSelectorWheel = false

                    btnIncrement.setOnClickListener {
                        bottomSheetCheckboxesBinding.numberPicker.value += 1
                    }

                    btnDecrement.setOnClickListener {
                        bottomSheetCheckboxesBinding.numberPicker.value -= 1
                    }

                    // QUANDO CLICAR NO BOTÃO
                    bottomSheetCheckboxesBinding.btnClick.setOnClickListener {
                        when (titulos) {
                            tituloLista[0] -> {
                                multiCampeaoGeral =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[1] -> {
                                multiViceGeral =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[2] -> {
                                multiTerceiroGeral =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[3] -> {
                                multiQuartoGeral =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[4] -> {
                                multiQuintoGeral =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[5] -> {
                                multiCampeaoMes =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[6] -> {
                                multiViceMes = numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[7] -> {
                                multiTerceiroMes =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[8] -> {
                                multiCampeaoCopa =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[9] -> {
                                multiViceCopa = numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[10] -> {
                                multiTerceiroCopa =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                            tituloLista[11] -> {
                                multiCampeaoTurno =
                                    numberPicker.value.toString().toIntOrNull() ?: 0
                            }
                        }

                        bottomSheetDialog.dismiss()
                    }
                    bottomSheetDialog.setContentView(bottomSheetCheckboxesBinding.root)
                    bottomSheetDialog.show()
                }

            }
        }
    }


    // SALVA AS INFORMAÇÕES NO BANCO DE DADOS

    private fun savePlayer() {
        FirebaseHelper
            .getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .setValue(players)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (newPlayer) { // Novo Jogador
                        showBottomSheetBack(
                            message = R.string.bs_message_player_created

                        )
                    } else { // Editando Jogador
                        showBottomSheetBack(
                            message = R.string.bs_message_player_updated,
                            titleDialog = R.string.text_update_bottom_sheet,
                        )

                    }
                } else {
                    showBottomSheet(
                        message = R.string.bs_message_player_error_generic,

                        )

                }
            }.addOnFailureListener {
                showBottomSheet(
                    message = R.string.bs_message_player_error_generic
                )

            }

        val tituloLista = arrayOf(
            binding.rdCampeaoGeral,
            binding.rdViceGeral,
            binding.rdTerceiroGeral,
            binding.rdQuartoGeral,
            binding.rdQuintoGeral,
            binding.rdCampeaoMes,
            binding.rdViceMes,
            binding.rdTerceiroMes,
            binding.rdCampeaoCopa,
            binding.rdViceCopa,
            binding.rdTerceiroCopa,
            binding.rdCampeaoTurno
        )

        for (titulos in tituloLista) {
            if (titulos.isChecked) {
                FirebaseHelper
                    .getDataBase()
                    .child(FirebaseHelper.ligaSanta)
                    .child(FirebaseHelper.getIdUser() ?: "")
                    .child(players.namePlayer)
                    .child("titulos")
                    .child(titulos.text.toString())
                    .setValue(titulos.isChecked)
            }

        }

        // Qtde Titulos
        for (titulos in tituloLista) {
            if (titulos.isChecked) {
                FirebaseHelper
                    .getDataBase()
                    .child(FirebaseHelper.ligaSanta)
                    .child(FirebaseHelper.getIdUser() ?: "")
                    .child(players.namePlayer)
                    .child("qtdeTitulos")
                    .child(titulos.text.toString())
                    .setValue(
                        when (titulos) {
                            tituloLista[0] -> {
                                multiCampeaoGeral
                            }
                            tituloLista[1] -> {
                                multiViceGeral
                            }
                            tituloLista[2] -> {
                                multiTerceiroGeral
                            }
                            tituloLista[3] -> {
                                multiQuartoGeral
                            }
                            tituloLista[4] -> {
                                multiQuintoGeral
                            }
                            tituloLista[5] -> {
                                multiCampeaoMes
                            }
                            tituloLista[6] -> {
                                multiViceMes
                            }
                            tituloLista[7] -> {
                                multiTerceiroMes
                            }
                            tituloLista[8] -> {
                                multiCampeaoCopa
                            }
                            tituloLista[9] -> {
                                multiViceCopa
                            }
                            tituloLista[10] -> {
                                multiTerceiroCopa
                            }
                            tituloLista[11] -> {
                                multiCampeaoTurno
                            }
                            else -> {}
                        }

                    )
            }
        }
    }

    // VALIDA AS INFORMAÇÕES
    private fun validateData() {
        binding.btnEnviar.setOnClickListener {
            val nomeJogador = binding.edtNomeJogador.text.toString().trim()
            val nomeTime = binding.edtNomeTime.text.toString().trim()

            // Verificação dos campos
            if (nomeJogador.isNotEmpty()) {
                if (nomeTime.isNotEmpty()) {
                    if (newPlayer) players = Players()
                    players.namePlayer = nomeJogador
                    players.nameClub = nomeTime

                    points()
                    savePlayer()
                } else {
                    showBottomSheet(
                        message = R.string.bs_message_player_name_error
                    )
                }
            } else {
                showBottomSheet(
                    message = R.string.bs_message_player_team_error
                )
            }
        }
    }

    private fun getArgs() {
        args.task.let {
            if (it != null) {
                players = it

                configPlayer()
            }
        }
    }

    private fun configPlayer() {
        newPlayer = false
        validador = false
        statusTask = players.status
        binding.txtToolbarFormPlayer.text = getString(R.string.txt_editar_jogador)
        binding.btnEnviar.text = getString(R.string.txt_atualizar_jogador)

        binding.edtNomeTime.setText(players.nameClub)
        binding.edtNomeJogador.setText(players.namePlayer)


        getValueCheckboxes()
        getPoints()


    }


    private fun points() {

        val pontosCampeaoGeral: Int = if (binding.rdCampeaoGeral.isChecked) {
            30 * multiCampeaoGeral
        } else 0

        val pontosViceGeral: Int = if (binding.rdViceGeral.isChecked) {
            10 * multiViceGeral
        } else 0
        val pontosTerceiroGeral: Int = if (binding.rdTerceiroGeral.isChecked) {
            5 * multiTerceiroGeral
        } else 0
        val pontosQuartoGeral: Int = if (binding.rdQuartoGeral.isChecked) {
            4 * multiQuartoGeral
        } else 0
        val pontosQuintoGeral: Int = if (binding.rdQuintoGeral.isChecked) {
            3 * multiQuintoGeral
        } else 0


        val pontosCampeaoMes: Int = if (binding.rdCampeaoMes.isChecked) {
            5 * multiCampeaoMes
        } else 0
        val pontosViceMes: Int = if (binding.rdViceMes.isChecked) {
            2 * multiViceMes
        } else 0
        val pontosTerceiroMes: Int = if (binding.rdTerceiroMes.isChecked) {
            1 * multiTerceiroMes
        } else 0


        val pontosCampeaoCopa: Int = if (binding.rdCampeaoCopa.isChecked) {
            3 * multiCampeaoCopa
        } else 0
        val pontosViceCopa: Int = if (binding.rdViceCopa.isChecked) {
            2 * multiViceCopa
        } else 0
        val pontosTerceiroCopa: Int = if (binding.rdTerceiroCopa.isChecked) {
            1 * multiTerceiroCopa
        } else 0

        val pontosCampeaoTurno: Int = if (binding.rdCampeaoTurno.isChecked) {
            10 * multiCampeaoTurno
        } else 0

        players.pontuacaoJogador =
            pontosCampeaoGeral +
                    pontosViceGeral +
                    pontosTerceiroGeral +
                    pontosQuartoGeral +
                    pontosQuintoGeral +
                    pontosCampeaoMes +
                    pontosViceMes +
                    pontosTerceiroMes +
                    pontosCampeaoCopa +
                    pontosViceCopa +
                    pontosTerceiroCopa +
                    pontosCampeaoTurno
    }

    private fun getValueCheckboxes() {

        FirebaseHelper.getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .child("titulos")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        //Geral
                        if (snap.key == binding.rdCampeaoGeral.text.toString()) binding.rdCampeaoGeral.isChecked =
                            true
                        if (snap.key == binding.rdViceGeral.text.toString()) binding.rdViceGeral.isChecked =
                            true
                        if (snap.key == binding.rdTerceiroGeral.text.toString()) binding.rdTerceiroGeral.isChecked =
                            true
                        if (snap.key == binding.rdQuartoGeral.text.toString()) binding.rdQuartoGeral.isChecked =
                            true
                        if (snap.key == binding.rdQuintoGeral.text.toString()) binding.rdQuintoGeral.isChecked =
                            true
                        //Mes
                        if (snap.key == binding.rdCampeaoMes.text.toString()) binding.rdCampeaoMes.isChecked =
                            true
                        if (snap.key == binding.rdViceMes.text.toString()) binding.rdViceMes.isChecked =
                            true
                        if (snap.key == binding.rdTerceiroMes.text.toString()) binding.rdTerceiroMes.isChecked =
                            true
                        //Copa
                        if (snap.key == binding.rdCampeaoCopa.text.toString()) binding.rdCampeaoCopa.isChecked =
                            true
                        if (snap.key == binding.rdViceCopa.text.toString()) binding.rdViceCopa.isChecked =
                            true
                        if (snap.key == binding.rdTerceiroCopa.text.toString()) binding.rdTerceiroCopa.isChecked =
                            true
                        //Turno
                        if (snap.key == binding.rdCampeaoTurno.text.toString()) binding.rdCampeaoTurno.isChecked =
                            true
                    }
                    validador = true
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getPoints() {
        FirebaseHelper.getDataBase()
            .child(FirebaseHelper.ligaSanta)
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(players.namePlayer)
            .child("qtdeTitulos")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        //Geral
                        if (snap.key == binding.rdCampeaoGeral.text.toString()) multiCampeaoGeral =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdViceGeral.text.toString()) multiViceGeral =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdTerceiroGeral.text.toString()) multiTerceiroGeral =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdQuartoGeral.text.toString()) multiQuartoGeral =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdQuintoGeral.text.toString()) multiQuintoGeral =
                            snap.value.toString().toIntOrNull() ?: 0
                        //Mes
                        if (snap.key == binding.rdCampeaoMes.text.toString()) multiCampeaoMes =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdViceMes.text.toString()) multiViceMes =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdTerceiroMes.text.toString()) multiTerceiroMes =
                            snap.value.toString().toIntOrNull() ?: 0
                        //Copa
                        if (snap.key == binding.rdCampeaoCopa.text.toString()) multiCampeaoCopa =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdViceCopa.text.toString()) multiViceCopa =
                            snap.value.toString().toIntOrNull() ?: 0
                        if (snap.key == binding.rdTerceiroCopa.text.toString()) multiTerceiroCopa =
                            snap.value.toString().toIntOrNull() ?: 0
                        //Turno
                        if (snap.key == binding.rdCampeaoTurno.text.toString()) multiCampeaoTurno =
                            snap.value.toString().toIntOrNull() ?: 0
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}