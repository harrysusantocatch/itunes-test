package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("resultCount") val totalSong : Int = 0,
    @SerializedName("results") val listSong : List<Song> = mutableListOf()
) : Serializable
