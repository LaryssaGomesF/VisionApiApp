package com.example.visionapiapp.domain.BarcodeScanner

interface BarcodeScanner {

    fun configureScanner()

    fun openCamera()

    fun analyzeImage()
}