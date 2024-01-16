package com.mmag.ud4_videoretrofitsimple.network.model


import com.google.gson.annotations.SerializedName

data class AllCharactersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>
)