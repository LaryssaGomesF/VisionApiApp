package com.example.visionapiapp.di

import com.example.visionapiapp.domain.permissions.PermissionRequestLauncher
import com.example.visionapiapp.domain.permissions.PermissionRequestLauncherImp
import com.example.visionapiapp.presentation.BarcodeViewModel
import com.example.visionapiapp.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesApp = module {


    viewModel { BarcodeViewModel() }

    viewModel { HomeViewModel() }

}