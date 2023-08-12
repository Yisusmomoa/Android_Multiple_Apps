package com.Brandon.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

//SerializedName permite darle un "alias" a la variable de la respuesta
//es recomenable dejar el serializedName y la variable con el mismo nombre
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>
)


data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String,
    @SerializedName("image") val image:superheroImageResponse
)

data class superheroImageResponse(@SerializedName("url") val url:String)