package com.example.visionapiapp.domain.permissions

import androidx.activity.result.ActivityResultLauncher

interface PermissionRequestLauncher {

    fun launchPermission(
        permissions: Array<String>,
        activityResultLauncher: ActivityResultLauncher<Array<String>>
    )
}
