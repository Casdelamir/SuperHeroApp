package com.example.superheroapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {
    @GET("search/{name}")
    suspend fun findSuperHeroesByName(@Path("name") query: String) : SuperHeroListResponse

    @GET("{id}")
    suspend fun findSuperHeroById(@Path("id") query: String) : SuperHero
}