package com.example.visionapiapp.domain.permissions

import android.net.Uri

sealed class PickImagesActions {



    data object RequestCameraPermission : PickImagesActions()


    data object OpenCamera : PickImagesActions()

}
