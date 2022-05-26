package com.example.jpmcnewyorkschools.data.model

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("dbn")
    val dbn: String
)