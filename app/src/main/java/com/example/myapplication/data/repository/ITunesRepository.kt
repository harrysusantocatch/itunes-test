package com.example.myapplication.data.repository

import com.example.myapplication.data.model.SearchResponse
import com.example.myapplication.data.remote.retrofit.model.NetworkResponse

interface ITunesRepository {
    suspend fun searchSong(term: String?): NetworkResponse<SearchResponse>
}