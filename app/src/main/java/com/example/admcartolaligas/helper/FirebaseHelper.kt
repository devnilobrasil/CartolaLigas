package com.example.admcartolaligas.helper

import com.example.admcartolaligas.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {


    // Forma de chamar a classe sem precisar instanciar
    companion object {

        const val ligaSanta : String = "ligaSanta"

        // Retorna a referência do banco de dados
        fun getDataBase() = FirebaseDatabase.getInstance().reference

        // Retorna a instância do usuário autenticado
        private fun getAuth() = FirebaseAuth.getInstance()

        // Retorna o ID do usuário autenticado
        fun getIdUser() = getAuth().uid

        // Verifica se o usuário está autenticado no app
        fun getIsAuthenticated() = getAuth().currentUser != null


        // Função utilizada no cadastro, recuperação de senha, login
        fun validError(error: String): Int {
            return when {
                // Erro de nenhuma conta encontrada - Login
                error.contains("There is no user record corresponding to this identifier") -> {
                    R.string.account_not_registered_register_fragment
                }
                // Erro de email mal escrito - Login
                error.contains("The email address is badly formatted") -> {
                    R.string.invalid_email_register_fragment
                }
                // Erro de senha inválida ou não existe - Login
                error.contains("The password is invalid or the user does not have a password") -> {
                    R.string.invalid_password_register_fragment
                }
                // Erro de email já cadastrado - Registro
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use_register_fragment
                }
                // Erro de senha fraca - Registro
                error.contains("Password should be at least 6 characters") -> {
                    R.string.strong_password_register_fragment
                }
                else -> {
                    // Retorna um erro genérico
                    R.string.error_generic
                }
            }
        }


    }
}