package com.example.superheroapp.activites

import android.content.Intent
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
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        SuperHeroServiceImplementation.searchByName("a", adapter, binding.noResults)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchViewItem = menu?.findItem(R.id.menu_search)
        val searchView = searchViewItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null) {
                        SuperHeroServiceImplementation.searchByName(newText, adapter, binding.noResults)
                    true
                } else false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    fun navigateToHeroDetails(superHero: SuperHero) {
            val intent = Intent(this, HeroDetailsActivity::class.java)
            intent.putExtra("id", superHero.id)
            startActivity(intent)
    }
}