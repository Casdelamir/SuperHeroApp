package com.example.superheroapp.activites

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapp.R
import com.example.superheroapp.adapters.SuperHeroAdapter
import com.example.superheroapp.data.SuperHero
import com.example.superheroapp.data.SuperHeroServiceImplementation
import com.example.superheroapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

lateinit var binding: ActivityMainBinding
lateinit var adapter: SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SuperHeroAdapter() { superHero ->
            navigateToHeroDetails(superHero)
        }

        binding.recyclerView.adapter = adapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        }

        SuperHeroServiceImplementation.searchByName("a", adapter, binding.noResults)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchViewItem = menu?.findItem(R.id.menu_search)
        val searchView = searchViewItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    SuperHeroServiceImplementation.searchByName(query, adapter, binding.noResults)
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    fun navigateToHeroDetails(superHero: SuperHero) {
            val intent = Intent(this, HeroDetailsActivity::class.java)
            intent.putExtra("id", superHero.id)
            startActivity(intent)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load landscape layout
            binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Load portrait layout
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }
}