package com.example.visionapiapp.domain.permissions

import androidx.activity.result.ActivityResultLauncher


class PermissionRequestLauncherImp(
    private val permissions: Array<String>,
    private val activityResultLauncher: ActivityResultLauncher<Array<String>>
) : PermissionRequestLauncher {
    override fun launchPermission() {
        activityResultLauncher.launch(permissions)
    }
}

