package com.example.superheroapp.data

import com.google.gson.annotations.SerializedName

class SuperHeroImageResponse(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
) {
}