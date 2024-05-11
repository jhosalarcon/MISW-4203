package com.misw.vinilos_g24.network

import android.content.Context
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.misw.vinilos_g24.models.Album
class CacheManager(context: Context) {
    companion object {
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var albums: ArrayMap<Int, List<Album>> = arrayMapOf<Int, List<Album>>()
    fun addAlbums(albumId: Int, album: List<Album>) {
        if (albums.containsKey(albumId)) {
            albums[albumId] = album
        }
    }

    fun getAlbums(albumId: Int): List<Album> {
        return if (albums.containsKey(albumId)) albums[albumId]!! else listOf<Album>()
    }

}