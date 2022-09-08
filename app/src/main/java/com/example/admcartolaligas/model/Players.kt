package com.example.admcartolaligas.model

import android.os.Parcelable
import com.example.admcartolaligas.databinding.FragmentFormPlayerBinding
import com.example.admcartolaligas.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class Players(
    // Informações: ID (para gerenciar no Firebase), Título
    var id: String = "",
    var nameClub: String = "",
    var namePlayer: String = "",

    /*
    var cGeral: Boolean = false,
    var vGeral: Boolean = false,
    var tGeral: Boolean = false,
    var qGeral: Boolean = false,
    var quintoGeral: Boolean = false,

    var cMes: Boolean = false,
    var vMes: Boolean = false,
    var tMes: Boolean = false,

    var cCopa: Boolean = false,
    var vCopa: Boolean = false,
    var tCopa: Boolean = false,

    var cTurno: Boolean = false,



    var multiCGeral: Int = 0,
    var multiVGeral: Int = 0,
    var multiTGeral: Int = 0,
    var multiQGeral: Int = 0,
    var multiQiGeral: Int = 0,

    var multiCCopa: Int = 0,
    var multiVCopa: Int = 0,
    var multiTCopa: Int = 0,

    var multiCMes: Int = 0,
    var multiVMes: Int = 0,
    var multiTMes: Int = 0,

    var multiCTurno: Int = 0,

     */

    var pontuacaoJogador: Int = 0,
    var status: Int = 0


) : Parcelable {
    // Toda vez que gerar uma tarefa nova, será gerado um ID automaticamente

    init {
        // Gerar um ID
        this.id = FirebaseHelper.getDataBase().push().key ?: ""
    }

}
