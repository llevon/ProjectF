package com.example.projectf.domain.utils

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<T>(val model: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
     object Loading : Resource<Nothing>()
}