package com.example.jpmcnewyorkschools.data.api

import com.example.jpmcnewyorkschools.data.model.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SchoolRepo {
    suspend fun getTheSchools(): Flow<ApiState>
    suspend fun getTheScores(): Flow<ApiState>
}

class SchoolRepoImpl(private val schoolService: SchoolService): SchoolRepo  {

    override suspend fun getTheSchools() = flow {
        emit(ApiState.Loading)

        val response = schoolService.getTheSchools()
        if (response.isSuccessful) {
            emit(ApiState.SuccessSchool(response.body()!!))
        } else {
            emit(ApiState.Error("Error getting schools"))
        }
    }

    override suspend fun getTheScores() = flow {
        emit(ApiState.Loading)

        val response = schoolService.getTheScores()
        if (response.isSuccessful) {
            emit(ApiState.SuccessScores(response.body()!!))
        } else {
            emit(ApiState.Error("Error getting scores"))
        }
    }
}