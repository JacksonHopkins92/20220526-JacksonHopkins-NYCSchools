package com.example.jpmcnewyorkschools.data.model

sealed class ApiState {
    data class SuccessSchool(val schoolResponse: List<School>): ApiState()
    data class SuccessScores(val scoreResponse: List<Score>): ApiState()
    data class Error(val errorMessage: String): ApiState()
    object Loading: ApiState()
}
