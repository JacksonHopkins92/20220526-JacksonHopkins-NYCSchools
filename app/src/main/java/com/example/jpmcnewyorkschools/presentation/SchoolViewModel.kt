package com.example.jpmcnewyorkschools.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jpmcnewyorkschools.data.api.SchoolRepo
import com.example.jpmcnewyorkschools.data.model.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SchoolViewModel(private val repository: SchoolRepo): ViewModel() {

    private val _schoolLiveData = MutableLiveData<ApiState>()
    val schoolLiveData : LiveData<ApiState>
        get() = _schoolLiveData

    private val _scoreLiveData = MutableLiveData<ApiState>()
    val scoreLiveData : LiveData<ApiState>
        get() = _scoreLiveData

    init {
        getTheSchools()
        getTheScores()
    }

    private fun getTheSchools() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getTheSchools().collect {
                _schoolLiveData.postValue(it)
            }
        }
    }

    private fun getTheScores() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getTheScores().collect {
                _scoreLiveData.postValue(it)
            }
        }
    }
}
