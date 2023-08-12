package com.Brandon.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //recibe el resto de la url si ocupa un parametro/params se coloca entre {}
    //y en el @PATH que es la ruta, vas a buscar y sustiuir lo que se llama name con la variable
    //y nos devuelve una response de x dato, para eso se crea la dataclass
    @GET("api/6479310845491487/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    @GET("api/6479310845491487/{id}")
    suspend fun getSuperheroById(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>

    // corrutinas: normalmente es secuencial
    // para eso existe el hilo principal
    // cuando se hacen llamadas al backend o una operación/calculo pesado como es algo que es muy pesado el télefono se puede bloquear y queda horrible
    // para eso existen las corrutinas que nos permiten hacer llamadas asincronas
    // guarda esto en la bd, me da igual cuando termine o avisame cuando termines la operación
    //supend fun para indicar que es una función asincrona
}