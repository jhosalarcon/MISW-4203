package com.misw.vinilos_g24.viewmodels

import ArtistaRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Artista

class ArtistasViewModel(private val repository: ArtistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Artistas"
    }
    val text: LiveData<String> = _text


    private val _artists = repository.artists
    val artists: MutableLiveData<List<Artista>?> = _artists

    suspend fun fetchArtists() {
        repository.fetchArtists()
    }

}