package com.example.myapplication.data.remote.di

import com.example.myapplication.data.remote.retrofit.service.ITunesService
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteServiceModules = module {

    factory { get<Retrofit>().create(ITunesService::class.java) }
}