package com.example.jpmcnewyorkschools.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jpmcnewyorkschools.data.model.School
import com.example.jpmcnewyorkschools.databinding.SchoolListItemBinding

class SchoolAdapter(private val schools: List<School>, val openSATPage: (String) -> Unit)
    : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    inner class SchoolViewHolder(private val binding: SchoolListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            binding.schoolSchoolCity.text = school.city
            binding.schoolSchoolName.text = school.schoolName
            binding.schoolViewScores.setOnClickListener {
                openSATPage(school.schoolName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            SchoolListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    override fun getItemCount(): Int {
        return schools.size
    }
}