package com.example.jpmcnewyorkschools.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jpmcnewyorkschools.databinding.ActivityMainBinding
import com.example.jpmcnewyorkschools.di.DI
import com.example.jpmcnewyorkschools.presentation.SchoolViewModel

class MainActivity : AppCompatActivity(){
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = DI.provideViewModel(this)
    }
}