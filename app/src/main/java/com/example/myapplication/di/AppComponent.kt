package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.remote.di.remoteModules
import com.example.myapplication.data.remote.di.remoteServiceModules
import com.example.myapplication.data.repository.di.repositoryModules

val appComponent = listOf(
    remoteModules(BuildConfig.API_BASE_URL, BuildConfig.DEBUG),
    remoteServiceModules,
    repositoryModules,
)