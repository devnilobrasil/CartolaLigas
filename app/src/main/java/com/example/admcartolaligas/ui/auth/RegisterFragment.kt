package com.example.admcartolaligas.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentRegisterBinding
import com.example.admcartolaligas.helper.FirebaseHelper
import com.example.admcartolaligas.helper.initToolbar
import com.example.admcartolaligas.helper.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun validateData() {
        val email = binding.edtEmailRegister.text.toString().trim()
        val password = binding.edtPasswordRegister.text.toString().trim()

        // Verificação dos campos
        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                // ProgressBar visível
                binding.progressBar.isVisible = true
                registerUser(email, password)
            } else {
                showBottomSheet(
                    message = R.string.bs_message_password
                )
            }
        } else {
            showBottomSheet(
                message = R.string.bs_message_email
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initToolbar(binding.tbCreateAccount)


        initClicks()

    }

    private fun initClicks() {
        binding.btnCriarConta.setOnClickListener { validateData() }
    }

    // Método responsável por salvar dos dados de registro no Firebase
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment) // Encaminhado para tela PRINCIPAL
                } else {
                    // Mensagem de erro na tela com base na classe FIREBASEHELPER
                    showBottomSheet(
                        message = FirebaseHelper.validError(task.exception?.message ?: "")
                    )
                    binding.progressBar.isVisible = false

                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}