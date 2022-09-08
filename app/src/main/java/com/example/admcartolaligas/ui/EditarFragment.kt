package com.example.admcartolaligas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentEditarBinding
import com.example.admcartolaligas.databinding.FragmentVerificarBinding
import com.google.firebase.auth.FirebaseAuth

class EditarFragment : Fragment() {

    private var _binding: FragmentEditarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}