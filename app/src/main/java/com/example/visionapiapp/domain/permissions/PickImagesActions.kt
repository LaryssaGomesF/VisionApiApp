package com.example.visionapiapp.domain.permissions

sealed class PickImagesActions {



    data object RequestCameraPermission : PickImagesActions()


    data object OpenCamera : PickImagesActions()

}
