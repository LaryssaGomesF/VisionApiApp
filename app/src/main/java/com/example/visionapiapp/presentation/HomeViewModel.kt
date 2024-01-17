package com.example.visionapiapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.visionapiapp.domain.permissions.PermissionStatus
import com.example.visionapiapp.domain.permissions.PickImagesActions

class HomeViewModel : ViewModel() {

    private val _action: MutableLiveData<PickImagesActions> = MutableLiveData()
    val action: LiveData<PickImagesActions> = _action


    fun onCameraPermissionUpdated(permissionStatus: PermissionStatus) {
        when (permissionStatus) {
            PermissionStatus.Granted -> sendAction { PickImagesActions.OpenCamera }
            else -> Unit
        }
    }

    private fun sendAction(block: () -> PickImagesActions) {
        _action.value = block()
    }

}