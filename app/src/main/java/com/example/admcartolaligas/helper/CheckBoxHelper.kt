package com.example.admcartolaligas.helper

import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.admcartolaligas.databinding.CustomDialogBinding
import com.example.admcartolaligas.databinding.FragmentFormPlayerBinding
import com.example.admcartolaligas.ui.FormPlayerFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CheckBoxHelper : Fragment() {

    companion object {
        fun teste(checkBox: CheckBox, formPlayerFragment: FormPlayerFragment) {
            FirebaseHelper.getDataBase()
                .child(FirebaseHelper.ligaSanta)
                .child(FirebaseHelper.getIdUser() ?: "")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (snap in snapshot.children) {
                            checkBox.isChecked = snap.child("titulos")
                                .child(checkBox.text.toString()).value.toString()
                                .toBoolean()

                            formPlayerFragment.validador = true
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
        }

    }
}