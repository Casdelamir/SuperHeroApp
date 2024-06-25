package com.example.superheroapp.data

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.example.superheroapp.adapters.SuperHeroAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroServiceImplementation {
companion object {
    fun searchByName(query: String, adapter: SuperHeroAdapter, textMessage: TextView) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(SuperheroApiService::class.java)
                val result = apiService.findSuperHeroesByName(query)
                //Log.i("HTTP", "${result.results}")
                Handler(Looper.getMainLooper()).post {
                    //code that runs in main
                    if (result.response == "success") {
                        textMessage.visibility = View.GONE
                        adapter.updateData(result.results)
                    } else {
                        textMessage.text = result.error
                        textMessage.visibility = View.VISIBLE
                        adapter.updateData(emptyList())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } /*catch (e: NullPointerException) {
                binding.noResults.text = error
                binding.noResults.visibility = View.VISIBLE
                adapter.updateData(emptyList())
                e.printStackTrace()
            }*/
        }
    }
//call labda function on the method
suspend fun findSuperHeroById(query: String): SuperHero? {
    return withContext(Dispatchers.IO) {
        try {
            val apiService = getRetrofit().create(SuperheroApiService::class.java)
            apiService.findSuperHeroById(query)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/ed1cd3aa1a195f3df2606042e3972110/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
}