package com.example.admcartolaligas.model

import android.os.Parcelable
import com.example.admcartolaligas.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampeaoGeral(

    //var id: String = "",

    var cGeral: Boolean = false,
    var multiCGeral: Int = 0,
    var status: Int = 0


) : Parcelable {
    // Toda vez que gerar uma tarefa nova, será gerado um ID automaticamente


}
