package com.mmag.ud4_videoretrofitsimple.network

import com.mmag.ud4_videoretrofitsimple.network.model.AllCharactersResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyService {

    //https://rickandmortyapi.com/api/character - baseUrl(https://rickandmortyapi.com/api/) = character
    @GET("character")
    fun getAllCharacters(): Call<AllCharactersResponse>

}