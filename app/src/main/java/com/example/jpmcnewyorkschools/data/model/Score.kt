package com.example.jpmcnewyorkschools.data.model

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("sat_critical_reading_avg_score")
    val readingAverage: String,
    @SerializedName("sat_math_avg_score")
    val mathAverage: String,
    @SerializedName("sat_writing_avg_score")
    val writingAverage: String,
    @SerializedName("dbn")
    val dbn: String
)