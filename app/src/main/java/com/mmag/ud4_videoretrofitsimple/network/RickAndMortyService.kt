package com.mmag.ud4_videoretrofitsimple.network

import com.mmag.ud4_videoretrofitsimple.network.model.AllCharactersResponse
import com.mmag.ud4_videoretrofitsimple.network.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    //https://rickandmortyapi.com/api/character - baseUrl(https://rickandmortyapi.com/api/) = character
    @GET("character")
    fun getAllCharacters(): Call<AllCharactersResponse>

    ///character/{id} -> character/23
    @GET("character/{id}")
    fun getSingleCharacter(@Path("id") id: Int): Call<Character>

}