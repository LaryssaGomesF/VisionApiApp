package com.example.visionapiapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.visionapiapp.databinding.FragmentBarcodeBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@ExperimentalGetImage class BarcodeFragment : Fragment() {
    private lateinit var binding: FragmentBarcodeBinding

    private lateinit var cameraSelector: CameraSelector
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: Preview

    private lateinit var imageAnalysis: ImageAnalysis

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBarcodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setUpCamera() {
        cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                processCameraProvider = cameraProviderFuture.get()
                bindCameraPreview()
                bindInputAnalyzer()
            },
            ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun bindCameraPreview() {
        cameraPreview =
            Preview.Builder().setTargetRotation(binding.viewFinder.display.rotation).build()
        cameraPreview.setSurfaceProvider(binding.viewFinder.surfaceProvider)
        processCameraProvider.bindToLifecycle(requireActivity(), cameraSelector, cameraPreview)
    }

    private fun bindInputAnalyzer() {
        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build()
        )
        imageAnalysis = ImageAnalysis.Builder()
            .setTargetRotation(binding.viewFinder.display.rotation)
            .build()
        val cameraExecutor = Executors.newSingleThreadExecutor()

        imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
            processImageProxy(
                barcodeScanner,
                imageProxy
            )

        }

        processCameraProvider.bindToLifecycle(requireActivity(), cameraSelector, imageAnalysis)
    }

    private fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy
    ) {
        val inputImage = imageProxy.image
        if(inputImage != null ){
            val mlInputImage = InputImage.fromMediaImage(
                inputImage,
                imageProxy.imageInfo.rotationDegrees
            )
            barcodeScanner.process(mlInputImage)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        barcodes.forEach {
                            Toast.makeText(requireContext(), it.rawValue.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }


}