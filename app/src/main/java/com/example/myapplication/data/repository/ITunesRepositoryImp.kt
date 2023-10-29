package com.example.myapplication.data.repository

import com.example.myapplication.data.model.SearchResponse
import com.example.myapplication.data.remote.retrofit.model.NetworkResponse
import com.example.myapplication.data.remote.retrofit.service.ITunesService

class ITunesRepositoryImp (
    private val service: ITunesService,
) : BaseRepository(), ITunesRepository {
    override suspend fun searchSong(term: String?): NetworkResponse<SearchResponse> {
        val response = safeApiCall(
            call = { service.searchSong(term) }
        )
        return if (response is NetworkResponse.Success) {
            NetworkResponse.Success(response.data)
        } else {
            response
        }
    }
}