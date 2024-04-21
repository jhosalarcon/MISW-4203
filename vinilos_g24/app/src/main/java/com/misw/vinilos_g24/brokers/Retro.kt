package com.misw.vinilos_g24.brokers

import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.models.Artista
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "http://10.0.2.2:3000/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
interface Retro {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("musicians")
    fun getArtists(): Call<List<Artista>>
}