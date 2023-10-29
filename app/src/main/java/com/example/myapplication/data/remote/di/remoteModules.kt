package com.example.myapplication.data.remote.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.myapplication.data.remote.retrofit.interceptor.NetworkConnectionInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

fun remoteModules(baseUrl: String, debug: Boolean) = module {

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (debug) {
                // Show Log for debug app
                HttpLoggingInterceptor.Level.BODY
            } else {
                // Hide Log for release app
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    factory {
        GsonBuilder().create()
    }
    factory {
        ChuckerInterceptor.Builder(get()).build()
    }

    factory {
        NetworkConnectionInterceptor(get())
    }

    factory {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
    }

    factory {
        val builder = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(get<NetworkConnectionInterceptor>())
        if (debug) {
            // Log if debugging is enabled
            builder.addInterceptor(get<ChuckerInterceptor>())
            builder.addNetworkInterceptor(get<HttpLoggingInterceptor>())
        }
        builder
    }

    factory<Retrofit> {
        get<Retrofit.Builder>().client(
            get<OkHttpClient.Builder>().build()
        ).build()
    }
}
