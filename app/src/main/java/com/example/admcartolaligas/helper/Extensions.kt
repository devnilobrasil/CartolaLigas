package com.example.admcartolaligas.helper

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.BottomSheetBinding
import com.example.admcartolaligas.databinding.BottomSheetCheckboxesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.initToolbar(toolbar: Toolbar) {
    // Inicialização de Toolbar em arquivos Fragments
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))


}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {}

) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val bottomSheetBinding: BottomSheetBinding =
        BottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinding.txtBsTitle.text =
        getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetBinding.txtBsMessage.text = getText(message)
    bottomSheetBinding.btnClick.text =
        getString(titleButton ?: R.string.text_button_bottom_sheet)
    bottomSheetBinding.btnClick.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    bottomSheetDialog.show()
}

fun Fragment.showBottomSheetBack(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {}

) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val bottomSheetBinding: BottomSheetBinding =
        BottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinding.txtBsTitle.text =
        getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetBinding.txtBsMessage.text = getText(message)
    bottomSheetBinding.btnClick.text =
        getString(titleButton ?: R.string.text_button_bottom_sheet)
    bottomSheetBinding.btnClick.setOnClickListener {
        findNavController().popBackStack()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    bottomSheetDialog.show()
}

fun Fragment.showBottomSheetCheckboxes(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {}

) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val bottomSheetCheckboxesBinding: BottomSheetCheckboxesBinding =
        BottomSheetCheckboxesBinding.inflate(layoutInflater, null, false)
    //val numberPicker = bottomSheetCheckboxesBinding.numberPicker
    //val fpf = FormPlayerFragment()
    //val binding:FragmentFormPlayerBinding = FragmentFormPlayerBinding.inflate(layoutInflater)


    // val formPlayerFragment = FormPlayerFragment()


    bottomSheetCheckboxesBinding.txtBsTitle.text =
        getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetCheckboxesBinding.txtBsMessage.text = getText(message)
    bottomSheetCheckboxesBinding.btnClick.text =
        getString(titleButton ?: R.string.text_button_bottom_sheet)
    bottomSheetCheckboxesBinding.btnClick.setOnClickListener {

        onClick()
        bottomSheetDialog.dismiss()

    }

    bottomSheetDialog.setContentView(bottomSheetCheckboxesBinding.root)
    bottomSheetDialog.show()
}



