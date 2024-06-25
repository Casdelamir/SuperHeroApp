package com.example.superheroapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SuperHero(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: Image
) {
}

class Image(
    @SerializedName("url") val url: String
){}