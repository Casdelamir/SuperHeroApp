package com.example.superheroapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.SuperHeroResponse
import com.example.superheroapp.databinding.ItemSuperHeroBinding
import com.squareup.picasso.Picasso

class SuperHeroAdapter(private var dataSet: List<SuperHeroResponse> = emptyList()) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = ItemSuperHeroBinding.inflate(LayoutInflater.from(parent.context))
        return SuperHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(dataSet: List<SuperHeroResponse>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    class SuperHeroViewHolder(private val binding: ItemSuperHeroBinding) : RecyclerView.ViewHolder(binding.root){
        fun render(superhero: SuperHeroResponse) {
            var id = superhero.id
            binding.nameTextView.text = superhero.name
            Picasso.get().load(superhero.image.url).into(binding.superHeroImage)
        }
    }
}