package com.example.myapplication.data.remote.retrofit.interceptor

import android.content.Context
import com.example.myapplication.data.remote.retrofit.exception.NetworkException
import com.example.myapplication.data.remote.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!NetworkUtil.isConnected(context)) {
            throw NetworkException()
        }
        return chain.proceed(request)
    }
}
