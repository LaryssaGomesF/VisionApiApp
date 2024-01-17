package com.example.visionapiapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.visionapiapp.databinding.FragmentHomeBinding
import com.example.visionapiapp.domain.permissions.PermissionStatus
import com.example.visionapiapp.domain.permissions.PickImagesActions
import com.example.visionapiapp.extensions.onLiveDataUpdate

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { accepted ->
            if(accepted){
                viewModel.onCameraPermissionUpdated(PermissionStatus.Granted)
            }else{
                viewModel.onCameraPermissionUpdated(PermissionStatus.Denied)
            }

        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpObservers()
    }


    private fun setUpListeners() = with(binding) {
        buttonOpenBarcodeScanner.setOnClickListener {
            cameraPermission.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun setUpObservers() {
        onLiveDataUpdate(viewModel.action, ::handleActions)
    }

    private fun handleActions(action: PickImagesActions) {
        when (action) {

            PickImagesActions.RequestCameraPermission -> {
                cameraPermission.launch(android.Manifest.permission.CAMERA)
            }

            PickImagesActions.OpenCamera -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBarcodeFragment())

        }
    }


}