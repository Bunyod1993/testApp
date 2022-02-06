package com.example.patient.utils.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CheckResult
import androidx.core.view.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun View.visible(){
    this.visibility=View.VISIBLE
}

fun View.invisible(){
    this.visibility=View.GONE
}
fun View.applyKeyboardInset() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val posBottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
        val pos = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        val status = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//            updateMargins(bottom=posBottom)
            updatePadding(top=status)
        }

        if (view is ViewGroup) {
            for (i in 0 until view.size) {
                ViewCompat.dispatchApplyWindowInsets(view[i], insets)
            }
        }
        insets
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun TextInputEditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}