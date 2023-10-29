package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.retrofit.exception.NetworkException
import com.example.myapplication.data.remote.retrofit.model.NetworkResponse
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ): NetworkResponse<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                when (response.code()) {
                    else -> parseError(response)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            parseException(ex)
        }
    }

    private fun parseException(ex: Exception): NetworkResponse.Error {
        return when (ex) {
            is NetworkException,
            is SocketTimeoutException,
            is SocketException -> NetworkResponse.Error("Network connection error")
            is UnknownHostException -> NetworkResponse.Error("Server responded with error. Please contact customer support.")
            else -> NetworkResponse.Error("Something went wrong!")
        }
    }

    private fun parseError(response: Response<*>): NetworkResponse.Error {
        return NetworkResponse.Error("Something went wrong!")
    }
}