package com.example.superheroapp.data

import android.util.JsonReader
import android.util.JsonWriter
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

class SuperHero(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: Image,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("powerstats") val powerstats: Powerstats
) {
}

class Image(
    @SerializedName("url") val url: String
){}

class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alter-egos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>
){}

class Powerstats(
    @JsonAdapter(FloatAdapter::class)@SerializedName("intelligence") val intelligence: Float,
    @JsonAdapter(FloatAdapter::class)@SerializedName("strength") val strength: Float,
    @JsonAdapter(FloatAdapter::class)@SerializedName("speed") val speed: Float,
    @JsonAdapter(FloatAdapter::class)@SerializedName("durability") val durability: Float,
    @JsonAdapter(FloatAdapter::class)@SerializedName("power") val power: Float,
    @JsonAdapter(FloatAdapter::class)@SerializedName("combat") val combat: Float
){}

class FloatAdapter : TypeAdapter<Float>() {
    override fun write(out: com.google.gson.stream.JsonWriter?, value: Float?) {
            out?.value(value)
    }

    override fun read(`in`: com.google.gson.stream.JsonReader?): Float {
        if (`in` != null) {
            val value: String = `in`.nextString()
            if (value != "null") {
                return value.toFloat()
            }
        }
        return 0F
    }

}
