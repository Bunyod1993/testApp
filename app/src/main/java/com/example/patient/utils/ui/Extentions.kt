package com.example.patient.utils.ui

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

fun View.visible(){
    this.visibility=View.VISIBLE
}

fun View.invisible(){
    this.visibility=View.GONE
}
fun View.applyKeyboardInset() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
//        val posBottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
//        val pos = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        val status = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(top=status)
        }

        if (view is ViewGroup) {
            for (i in 0 until view.size) {
                ViewCompat.dispatchApplyWindowInsets(view[i], insets)
            }
        }
        insets
    }
}