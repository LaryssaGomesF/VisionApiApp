package com.example.visionapiapp.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LifecycleOwner.onLiveDataUpdate(
    liveData: LiveData<T>,
    crossinline callback: (T) -> Unit
) {
    liveData.observe(this, Observer { changedValue ->
        callback(changedValue)
    })
}
