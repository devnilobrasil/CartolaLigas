package com.example.admcartolaligas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentLoginBinding
import com.example.admcartolaligas.databinding.FragmentVerificarBinding
import com.example.admcartolaligas.model.Informacoes
import com.example.admcartolaligas.ui.adapter.InformacoesAdapter
import com.google.firebase.auth.FirebaseAuth

class InformativoFragment : Fragment() {

    private var _binding: FragmentVerificarBinding? = null
    private val binding get() = _binding!!

    private lateinit var informacoesAdapter: InformacoesAdapter
    private val listaInformacoes : MutableList<Informacoes> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerificarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }


    private fun initAdapter(){

        informacoesTitulosGeral()

    }

    private fun informacoesTitulosGeral(){
        binding.rvInformacoesGeral.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInformacoesGeral.setHasFixedSize(true)
        informacoesAdapter = InformacoesAdapter(requireContext(), listaInformacoes)
        binding.rvInformacoesGeral.adapter = informacoesAdapter

        val campeaoGeral = Informacoes(R.drawable.c_geral, R.string.txt_campeao_geral, R.string.pt_campeao_geral)
        listaInformacoes.add(campeaoGeral)

        val viceGeral = Informacoes(R.drawable.v_geral, R.string.txt_vice_geral, R.string.pt_vice_geral)
        listaInformacoes.add(viceGeral)

        val terceiroGeral = Informacoes(R.drawable.t_geral, R.string.txt_terceiro_geral, R.string.pt_terceiro_geral)
        listaInformacoes.add(terceiroGeral)

        val quartoGeral = Informacoes(R.drawable.q_geral, R.string.txt_quarto_geral, R.string.pt_quarto_geral)
        listaInformacoes.add(quartoGeral)

        val quintoGeral = Informacoes(R.drawable.qi_geral, R.string.txt_quinto_geral, R.string.pt_quinto_geral)
        listaInformacoes.add(quintoGeral)

        val campeaoMes = Informacoes(R.drawable.c_mes, R.string.txt_campeao_mes, R.string.pt_campeao_mes)
        listaInformacoes.add(campeaoMes)

        val viceMes = Informacoes(R.drawable.v_mes, R.string.txt_vice_mes, R.string.pt_vice_mes)
        listaInformacoes.add(viceMes)

        val terceiroMes = Informacoes(R.drawable.t_mes, R.string.txt_terceiro_mes, R.string.pt_terceiro_mes)
        listaInformacoes.add(terceiroMes)

        val campeaoCopa = Informacoes(R.drawable.c_copa, R.string.txt_campeao_copa, R.string.pt_campeao_copa)
        listaInformacoes.add(campeaoCopa)

        val viceCopa = Informacoes(R.drawable.v_copa, R.string.txt_vice_copa, R.string.pt_vice_copa)
        listaInformacoes.add(viceCopa)

        val terceiroCopa = Informacoes(R.drawable.t_copa, R.string.txt_terceiro_copa, R.string.pt_terceiro_copa)
        listaInformacoes.add(terceiroCopa)

        val campeaoTurno = Informacoes(R.drawable.c_turno, R.string.txt_campeao_turno, R.string.pt_campeao_turno)
        listaInformacoes.add(campeaoTurno)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}