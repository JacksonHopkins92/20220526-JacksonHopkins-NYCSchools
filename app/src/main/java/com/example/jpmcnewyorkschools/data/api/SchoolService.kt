package com.example.jpmcnewyorkschools.data.api

import com.example.jpmcnewyorkschools.data.model.School
import com.example.jpmcnewyorkschools.data.model.Score
import com.example.jpmcnewyorkschools.util.SAT_ENDPOINT
import com.example.jpmcnewyorkschools.util.SCHOOL_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface SchoolService {

    @GET(SCHOOL_ENDPOINT)
    suspend fun getTheSchools(): Response<List<School>>

    @GET(SAT_ENDPOINT)
    suspend fun getTheScores(): Response<List<Score>>
}