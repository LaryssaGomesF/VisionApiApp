package com.example.visionapiapp.domain.BarcodeScanner

import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode

class BarcodeScannerImp: BarcodeScanner {

    override fun configureScanner() {
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .enableAllPotentialBarcodes()
            .build()
    }

    override fun openCamera() {
        TODO("Not yet implemented")
    }

    override fun analyzeImage() {
        TODO("Not yet implemented")
    }
}