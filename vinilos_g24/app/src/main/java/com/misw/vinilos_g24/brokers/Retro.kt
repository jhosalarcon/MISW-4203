package com.misw.vinilos_g24.brokers

import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.models.Artista
import retrofit2.Call
import retrofit2.http.GET

interface Retro {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("musicians")
    fun getArtists(): Call<List<Artista>>
}