package com.example.superheroapp.data

import com.google.gson.annotations.SerializedName

class SuperHeroListResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<SuperHero>,
    @SerializedName("error") val error: String) {

}