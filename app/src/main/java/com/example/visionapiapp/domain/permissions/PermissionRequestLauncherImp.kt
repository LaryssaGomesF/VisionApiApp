package com.example.visionapiapp.domain.permissions

import androidx.activity.result.ActivityResultLauncher

class PermissionRequestLauncherImp(

) : PermissionRequestLauncher {
    override fun launchPermission(
        permissions: Array<String>,
        activityResultLauncher: ActivityResultLauncher<Array<String>>
    ) {
        activityResultLauncher.launch(permissions)
    }
}
