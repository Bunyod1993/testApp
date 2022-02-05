package com.example.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patient.databinding.PatientEmptyBinding
import com.example.patient.databinding.PatientListItemBinding

class FilterAdapter(private val list: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType > 0) {
            val item =
                PatientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PatientViewHolder(item)
        } else {
            val empty =
                PatientEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PatientEmptyViewHolder(empty)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = if (list.isEmpty()) 1 else list.size

    override fun getItemViewType(position: Int): Int {
        return list.size
    }

    inner class PatientViewHolder(private val patientListItemBinding: PatientListItemBinding) :
        RecyclerView.ViewHolder(patientListItemBinding.root) {
        fun setBinding() {

        }
    }

    inner class PatientEmptyViewHolder(private val empty: PatientEmptyBinding) :
        RecyclerView.ViewHolder(empty.root) {
        fun setBinding() {

        }
    }
}