package com.example.admcartolaligas.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.BottomSheetCheckboxesBinding
import com.example.admcartolaligas.databinding.FragmentFormPlayerBinding
import com.example.admcartolaligas.ui.FormPlayerFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

data class Pontos(
    var nomeTitulo : String? = null,
    var pontosTitulo : Int? = null
) {

}