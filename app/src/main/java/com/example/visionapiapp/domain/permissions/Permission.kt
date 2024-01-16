package com.example.visionapiapp.domain.permissions

import android.Manifest
import android.os.Build

sealed class Permission(vararg val permissions: String) {

    data object Camera : Permission(Manifest.permission.CAMERA)

    data object Storage : Permission(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    data object StorageVersionCodeTiramisu : Permission(
        Manifest.permission.READ_MEDIA_IMAGES
    )


    companion object {
        const val CAMERA_PERMISSION = "CAMERA_PERMISSION"
        const val STORAGE_PERMISSION = "STORAGE_PERMISSION"


        fun from(permission: String) = when (permission) {
            STORAGE_PERMISSION -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    StorageVersionCodeTiramisu
                } else {
                    Storage
                }
            }

            CAMERA_PERMISSION -> {
                Camera
            }

            else -> {
                throw IllegalArgumentException("Unknown permission: $permission")
            }
        }
    }
}