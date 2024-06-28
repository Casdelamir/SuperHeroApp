package com.example.superheroapp.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroapp.R
import com.example.superheroapp.data.SuperHeroServiceImplementation
import com.example.superheroapp.databinding.ActivityHeroDetailsBinding
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.squareup.picasso.Picasso


class HeroDetailsActivity : AppCompatActivity() {
    lateinit var superHeroId: String
    lateinit var binding: ActivityHeroDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        superHeroId = intent.getStringExtra("id").toString()

        //To turn the card and display other data on click use animate function and turn
        //the cart to 90 degrees and then turn it back to the same face but with different data visible
        //
        //To create another layout for the landscape click on the

        supportActionBar?.hide()
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

            SuperHeroServiceImplementation.findSuperHeroById(superHeroId) {
                superHero ->

                binding.nameHero.text = superHero.name

                Picasso.get()
                    .load(superHero.image.url)
                    .placeholder(R.drawable.baseline_downloading_24)
                    .error(R.drawable.baseline_error_24)
                    .into(binding.imageHero)

                binding.fullNameHero.text = getString(R.string.full_name, superHero.biography.fullName)
                binding.alterEgoHero.text = getString(R.string.alter_ego, superHero.biography.alterEgos)

                val aliases: String = superHero.biography.aliases.joinToString(separator = ", ")
                binding.aliases.text = getString(R.string.aliases, aliases)


                val listRadarEntries = ArrayList<RadarEntry>()
                listRadarEntries.add(RadarEntry(superHero.powerstats.intelligence))
                listRadarEntries.add(RadarEntry(superHero.powerstats.strength))
                listRadarEntries.add(RadarEntry(superHero.powerstats.speed))
                listRadarEntries.add(RadarEntry(superHero.powerstats.durability))
                listRadarEntries.add(RadarEntry(superHero.powerstats.power))
                listRadarEntries.add(RadarEntry(superHero.powerstats.combat))

                val dataSet = RadarDataSet(listRadarEntries, "Label") // add entries to dataset
                dataSet.color = getColor(R.color.blue)
                dataSet.fillColor = getColor(R.color.green)
                dataSet.setDrawFilled(true)
                dataSet.valueTextColor = getColor(R.color.red)
                dataSet.valueTextSize = 12f
                dataSet.fillAlpha = 80

                // Customizing the X axis labels
                val xAxis = binding.chart.xAxis
                val labels = arrayOf("Intelligence", "Strength", "Speed", "Durability", "Power", "Combat")
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                xAxis.textSize = 12f
                xAxis.textColor = getColor(R.color.black)

                // Customizing the Y axis
                val yAxis = binding.chart.yAxis
                yAxis.isEnabled = false
                yAxis.axisMaximum = 100f
                yAxis.axisMinimum = 0f
                yAxis.setLabelCount(6, true)
                yAxis.textSize = 12f

                // Other chart customizations
                binding.chart.description.isEnabled = false
                binding.chart.webLineWidth = 1f
                binding.chart.webLineWidthInner = 1f
                binding.chart.animateXY(1400, 1400)

                val radarData = RadarData(dataSet)
                binding.chart.data = radarData
                //Remove bottom legend of labels
                binding.chart.legend.isEnabled = false
                binding.chart.invalidate() // refresh
            }
    }
}