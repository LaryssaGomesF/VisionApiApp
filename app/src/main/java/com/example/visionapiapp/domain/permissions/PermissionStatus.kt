package com.example.visionapiapp.domain.permissions

sealed class PermissionStatus {
    data object Granted : PermissionStatus()
    data object Denied : PermissionStatus()
}
