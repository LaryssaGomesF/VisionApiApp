package com.example.visionapiapp.di

import com.example.visionapiapp.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesApp = module {

    viewModel { HomeViewModel() }

}