package com.example.visionapiapp.di

import com.example.visionapiapp.domain.permissions.PermissionRequestLauncher
import com.example.visionapiapp.domain.permissions.PermissionRequestLauncherImp
import com.example.visionapiapp.presentation.BarcodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesApp = module {

    factory<PermissionRequestLauncher> { PermissionRequestLauncherImp()  }

    viewModel { BarcodeViewModel(get()) }

}