package com.example.patient.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.patient.R

class ContentFragment : Fragment() {
    private lateinit var img:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         img=view.findViewById(R.id.img)
        arguments?.takeIf { it.containsKey(slideNumber) }?.apply {
            changeContent(this.getInt(slideNumber))
        }

    }

    private fun changeContent(page: Int) {
        when (page) {
            1 -> {
                img.setBackgroundColor(resources.getColor(R.color.black_overlay))
            }
            2 -> {
                img.setBackgroundColor(resources.getColor(R.color.greenPale))
            }
            else -> {
                img.setBackgroundColor(resources.getColor(R.color.blue))
            }
        }
    }

    companion object {
        const val slideNumber = "number"
    }
}