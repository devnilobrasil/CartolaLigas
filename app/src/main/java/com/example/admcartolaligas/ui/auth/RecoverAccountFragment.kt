package com.example.admcartolaligas.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentRecoverAccountBinding
import com.example.admcartolaligas.helper.FirebaseHelper
import com.example.admcartolaligas.helper.initToolbar
import com.example.admcartolaligas.helper.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

         initToolbar(binding.tbRecoveryAccount)

        initClicks()

    }

    private fun initClicks() {
        binding.btnEnviar.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.edtEmailRecovery.text.toString().trim()

        // Verificação dos campos
        if (email.isNotEmpty()) {
            binding.progressBar.isVisible = true
            recoverUser(email)
        } else {
            showBottomSheet(
                message = R.string.bs_message_email
            )
        }

    }

    private fun recoverUser(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    showBottomSheet(
                        message = R.string.bs_recover_email_sucessfull
                    )
                } else{
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