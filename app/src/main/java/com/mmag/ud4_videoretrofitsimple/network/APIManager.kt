package com.mmag.ud4_videoretrofitsimple.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {
    //Parsea los JSONS a data class
    private val converter = GsonConverterFactory.create()

    //Asigna el nivel de detalle que queremos de las peticiones por consola
    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Nos carga el interceptor
    private val client = OkHttpClient.Builder().addInterceptor(logginInterceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/") //Tiene que terminar siempre en /
        .client(client)
        .addConverterFactory(converter)
        .build()


    val service: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }
}