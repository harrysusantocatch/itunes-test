package com.example.myapplication.data.repository.di

import com.example.myapplication.data.repository.ITunesRepository
import com.example.myapplication.data.repository.ITunesRepositoryImp
import org.koin.dsl.module

val repositoryModules = module {

    factory<ITunesRepository> {
        ITunesRepositoryImp(get())
    }
}