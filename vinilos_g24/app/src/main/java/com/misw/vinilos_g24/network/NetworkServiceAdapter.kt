package com.misw.vinilos_g24.network

import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.models.Coleccionista
import retrofit2.Call
import retrofit2.http.GET

interface NetworkServiceAdapter {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("musicians")
    fun getArtists(): Call<List<Artista>>

    @GET("collectors")
    fun getCollectors(): Call<List<Coleccionista>>
}