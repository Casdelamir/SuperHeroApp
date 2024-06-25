package com.example.superheroapp.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.superheroapp.R
import com.example.superheroapp.data.SuperHero
import com.example.superheroapp.data.SuperHeroServiceImplementation
import com.example.superheroapp.data.SuperheroApiService
import com.example.superheroapp.databinding.ActivityHeroDetailsBinding
import com.example.superheroapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class HeroDetails : AppCompatActivity() {
    lateinit var superHeroId: String
    lateinit var binding: ActivityHeroDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)

        superHeroId = intent.getStringExtra("id")!!

        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.IO).launch {
            val superHero = SuperHeroServiceImplementation.findSuperHeroById(superHeroId)!!

                runOnUiThread {
                    binding.nameHero.text = superHero.name

                    Picasso.get()
                        .load(superHero.image.url)
                        .placeholder(R.drawable.baseline_downloading_24)
                        .error(R.drawable.baseline_error_24)
                        .into(binding.imageHero)
                }
        }
    }
}