package com.misw.vinilos_g24.viewmodels

import ArtistaRepository
import NetworkServiceAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misw.vinilos_g24.models.Artista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistasViewModel(private val repository: ArtistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Artistas"
    }
    val text: LiveData<String> = _text


    private val _artists = repository.artists
    val artists: MutableLiveData<List<Artista>?> = _artists

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    private val apiService: NetworkServiceAdapter = TODO()

    private val artistRepository = ArtistaRepository(apiService)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = artistRepository.refreshData()
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: Exception) {
            _eventNetworkError.value = true
        }
    }

    suspend fun fetchArtists() {
        repository.fetchArtists()
    }

}