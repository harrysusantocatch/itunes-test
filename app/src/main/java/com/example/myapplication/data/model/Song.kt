package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Song(
    @SerializedName("trackId") val trackId : Int,
    @SerializedName("artistName") val artist : String,
    @SerializedName("collectionName") val album : String,
    @SerializedName("trackName") val songName : String,
    @SerializedName("artworkUrl100") val image : String,
    @SerializedName("previewUrl") val preview : String,
) : Serializable