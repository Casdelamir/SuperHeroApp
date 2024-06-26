package com.example.superheroapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SuperHero(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: Image,
    //@SerializedName("biography") val biography: Biography
) {
}

class Image(
    @SerializedName("url") val url: String
){}

/*
class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alter-egos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>
){}

 */