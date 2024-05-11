package com.misw.vinilos_g24.viewmodels

import AlbumRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Album

class AlbumesViewModel(private val repository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Álbumes"
    }
    val text: LiveData<String> = _text

    private val _albums = repository.albums
    val albums: MutableLiveData<List<Album>?> = _albums

    suspend fun fetchAlbums() {
        repository.fetchAlbums()
    }

}