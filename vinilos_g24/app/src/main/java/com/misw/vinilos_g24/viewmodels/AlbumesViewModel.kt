package com.misw.vinilos_g24.viewmodels

import AlbumRepository
import NetworkServiceAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misw.vinilos_g24.models.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumesViewModel(private val repository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Álbumes"
    }
    private val apiService: NetworkServiceAdapter = TODO()

    val text: LiveData<String> = _text
    private val albumsRepository = AlbumRepository(apiService)

    val albums: MutableLiveData<List<Album>?>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private val _albums = repository.albums

    suspend fun fetchAlbums() {
        repository.fetchAlbums()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    albumsRepository.refreshData()
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: Exception) {
            _eventNetworkError.value = true
        }
    }
}


