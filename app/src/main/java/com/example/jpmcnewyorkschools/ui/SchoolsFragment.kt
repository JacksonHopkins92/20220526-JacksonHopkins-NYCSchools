package com.example.jpmcnewyorkschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jpmcnewyorkschools.R
import com.example.jpmcnewyorkschools.data.model.ApiState
import com.example.jpmcnewyorkschools.databinding.SchoolsFragmentBinding
import com.example.jpmcnewyorkschools.presentation.SchoolViewModel

class SchoolsFragment: Fragment() {

    private var _binding: SchoolsFragmentBinding? = null
    private val binding: SchoolsFragmentBinding
        get() = _binding!!

    private lateinit var viewModel: SchoolViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SchoolsFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
        startObserving()
        return binding.root
    }

    private fun startObserving() {
        viewModel.schoolLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is ApiState.SuccessSchool -> {
                    binding.progressBar.visibility = View.GONE
                    binding.schoolErrorText.visibility = View.GONE
                    binding.recyclerView.adapter = SchoolAdapter(it.schoolResponse, ::openSATPage)
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is ApiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.schoolErrorText.text = it.errorMessage
                    binding.schoolErrorText.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is ApiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.schoolErrorText.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }
    }

    private fun openSATPage(school: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ScoresFragment.newInstance(school))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}