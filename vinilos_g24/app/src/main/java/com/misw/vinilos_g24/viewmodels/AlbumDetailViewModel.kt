package com.misw.vinilos_g24.viewmodels

import AlbumRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Album

class AlbumDetailViewModel(private val repository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Detalle de album"
    }
    val text: LiveData<String> = _text

    private val _albumDetails = repository.albumDetails
    val albumDetails: MutableLiveData<Album?> = _albumDetails
    suspend fun fetchAlbumDetails(albumId: Int) {
        repository.fetchAlbumDetails(albumId)
    }

}