package com.example.myapplication.data.remote.retrofit.model

sealed class NetworkResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()

    data class Error(val error: String, val status: Int = 0) : NetworkResponse<Nothing>()
}