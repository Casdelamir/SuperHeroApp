package com.example.superheroapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.data.SuperHero
import com.example.superheroapp.databinding.ItemSuperHeroBinding
import com.squareup.picasso.Picasso


class SuperHeroAdapter(private var dataSet: List<SuperHero> = emptyList(), private val onClickListener: (SuperHero) -> Unit) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = ItemSuperHeroBinding.inflate(LayoutInflater.from(parent.context))
        return SuperHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        // Here we pass the lambda function onClickListener to the render function and execute it there
        holder.render(dataSet[position], onClickListener)
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(dataSet: List<SuperHero>) {
            this.dataSet = dataSet
            notifyDataSetChanged()
    }

    class SuperHeroViewHolder(private val binding: ItemSuperHeroBinding) : RecyclerView.ViewHolder(binding.root){
        fun render(superhero: SuperHero, onClickListener: (SuperHero) -> Unit) {
            binding.nameTextView.text = superhero.name

            binding.cardItemSuperHero.setOnClickListener {
                onClickListener(superhero)
            }

            Picasso.get()
                .load(superhero.image.url)
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_24)
                .into(binding.superHeroImage)
        }
    }
}