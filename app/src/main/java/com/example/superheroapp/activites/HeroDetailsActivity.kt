package com.example.superheroapp.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.superheroapp.R
import com.example.superheroapp.data.SuperHeroServiceImplementation
import com.example.superheroapp.databinding.ActivityHeroDetailsBinding
import com.squareup.picasso.Picasso

class HeroDetailsActivity : AppCompatActivity() {
    lateinit var superHeroId: String
    lateinit var binding: ActivityHeroDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        superHeroId = intent.getStringExtra("id").toString()

            SuperHeroServiceImplementation.findSuperHeroById(superHeroId) {
                superHero ->

                binding.nameHero.text = superHero.name

                Picasso.get()
                    .load(superHero.image.url)
                    .placeholder(R.drawable.baseline_downloading_24)
                    .error(R.drawable.baseline_error_24)
                    .into(binding.imageHero)

                //binding.fullNameHero.text = getString(R.string.full_name, superHero.biography.fullName)
            }
    }
}