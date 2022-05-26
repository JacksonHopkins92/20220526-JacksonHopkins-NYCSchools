package com.example.jpmcnewyorkschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jpmcnewyorkschools.data.model.ApiState
import com.example.jpmcnewyorkschools.data.model.Score
import com.example.jpmcnewyorkschools.databinding.ScoresFragmentBinding
import com.example.jpmcnewyorkschools.presentation.SchoolViewModel

class ScoresFragment: Fragment() {

    companion object {
        const val KEY = "KEY"
        fun newInstance(school:String) : ScoresFragment {
            val fragment = ScoresFragment()
            val bundle = Bundle()
            bundle.putString(KEY, school)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding : ScoresFragmentBinding? = null
    private val binding: ScoresFragmentBinding
        get() = _binding!!

    lateinit var viewModel: SchoolViewModel
    var schoolId = ""
    var score: Score? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ScoresFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
        schoolId = arguments?.getString(KEY).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.scoreLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is ApiState.SuccessScores -> {
                    score = getThisScore(it.scoreResponse)
                    if (score == null) {
                        binding.scoresErrorText.visibility = View.VISIBLE
                    } else {
                        binding.scoreSchoolName.text = score?.schoolName
                        binding.scoreMath.text = "Math: ${score?.mathAverage}"
                        binding.scoreReading.text = "Reading: ${score?.readingAverage}"
                        binding.scoreWriting.text = "Writing: ${score?.writingAverage}"
                    }
                }
            }
        }
    }

    private fun getThisScore(scores: List<Score>): Score? {
        return scores.firstOrNull{
            it.dbn == schoolId
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}