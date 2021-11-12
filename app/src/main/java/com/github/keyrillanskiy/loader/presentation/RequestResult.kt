package com.github.keyrillanskiy.loader.presentation

sealed class RequestResult<T> {
    data class Success<T>(val data: T) : RequestResult<T>()
    class Loading<T> : RequestResult<T>()
}
