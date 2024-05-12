package com.misw.vinilos_g24.viewmodels

import ArtistaRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Artista


class ArtistaDetailViewModel(private val repository: ArtistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Detalle de artista"
    }
    val text: LiveData<String> = _text

    private val _artistDetails = repository.artistDetails
    val artistDetails: MutableLiveData<Artista?> = _artistDetails
    suspend fun fetchArtistDetails(artistId: Int) {
        repository.fetchArtistaDetails(artistId)
    }

}