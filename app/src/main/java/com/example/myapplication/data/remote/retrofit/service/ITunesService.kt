package com.example.myapplication.data.remote.retrofit.service

import com.example.myapplication.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ITunesService {

    @Headers("Accept: application/json")
    @GET("search")
    suspend fun searchSong(
        @Query("term") term: String?
    ): Response<SearchResponse>
}
