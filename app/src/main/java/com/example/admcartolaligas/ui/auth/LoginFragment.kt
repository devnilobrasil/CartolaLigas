package com.example.admcartolaligas.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentLoginBinding
import com.example.admcartolaligas.helper.FirebaseHelper
import com.example.admcartolaligas.helper.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClicks()

    }

    private fun initClicks() {
        binding.txtCriarConta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.txtRecuperarConta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
        binding.btnLogin.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.edtEmailLogin.text.toString().trim()
        val password = binding.edtPasswordLogin.text.toString().trim()

        // Verificação dos campos
        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                // ProgressBar visível
                binding.progressBar.isVisible = true
                loginUser(email, password)
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

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment) // Encaminhado para tela PRINCIPAL
                } else {
                    // Mensagem de erro na tela com base na classe FIREBASEHELPER via BOTTOM SHEET DIALOG
                    showBottomSheet(
                        message = FirebaseHelper.validError(task.exception?.message ?: ""),
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
