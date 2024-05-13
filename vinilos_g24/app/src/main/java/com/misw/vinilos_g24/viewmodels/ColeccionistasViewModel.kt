package com.misw.vinilos_g24.viewmodels

import ColeccionistaRepository
import NetworkServiceAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misw.vinilos_g24.models.Coleccionista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColeccionistasViewModel(private val repository: ColeccionistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coleccionista"
    }
    val text: LiveData<String> = _text

    private val _coleccionista = repository.collectors
    val collectors: MutableLiveData<List<Coleccionista>?> = _coleccionista


    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    private val apiService: NetworkServiceAdapter = TODO()

    private val collectorRepository = ColeccionistaRepository(apiService)
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
                    var data = collectorRepository.refreshData()
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: Exception) {
            _eventNetworkError.value = true
        }
    }
    suspend fun fetchCollectors() {
        repository.fetchCollectors()
    }

}
