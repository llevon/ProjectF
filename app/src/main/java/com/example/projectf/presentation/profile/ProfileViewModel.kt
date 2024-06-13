package com.example.projectf.presentation.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel:ViewModel() {
    private val _profilePictureUri = MutableLiveData<Uri?>()
    val profilePictureUri: LiveData<Uri?> get() = _profilePictureUri
    fun setProfilePictureUri(uri: Uri?) {
        _profilePictureUri.value = uri
    }
}