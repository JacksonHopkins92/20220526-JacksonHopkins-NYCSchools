package com.example.jpmcnewyorkschools.di

import com.google.gson.annotations.SerializedName
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.jpmcnewyorkschools.data.api.SchoolRepoImpl
import com.example.jpmcnewyorkschools.data.api.SchoolService
import com.example.jpmcnewyorkschools.presentation.SchoolViewModel
import com.example.jpmcnewyorkschools.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DI {

    private val service: SchoolService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolService::class.java)
    }

    private fun getSchoolRepoImpl() = SchoolRepoImpl(service)

    fun provideViewModel(storeOwner: ViewModelStoreOwner) =
        createSchoolViewModel(storeOwner)[SchoolViewModel::class.java]

    private fun createSchoolViewModel(storeOwner: ViewModelStoreOwner) =
        ViewModelProvider(storeOwner, object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SchoolViewModel(getSchoolRepoImpl()) as T
            }
        })
}