package com.example.visionapiapp.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.visionapiapp.domain.permissions.Permission


fun MutableList<Permission>.getPermissionList() =
    this.flatMap { it.permissions.toList() }

fun MutableList<Permission>.getPermissionArray() =
    this.flatMap { it.permissions.toList() }.toTypedArray()

private fun Permission.isGranted(context: Context) =
    permissions.all { hasPermission(context, it) }

private fun hasPermission(context: Context, permission: String) =
    ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED

fun MutableList<Permission>.areAllPermissionsGranted(context: Context) =
    this.all { it.isGranted(context) }