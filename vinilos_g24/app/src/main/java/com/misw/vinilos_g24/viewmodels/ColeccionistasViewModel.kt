package com.misw.vinilos_g24.viewmodels

import ColeccionistaRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Coleccionista

class ColeccionistasViewModel(private val repository: ColeccionistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coleccionista"
    }
    val text: LiveData<String> = _text

    private val _coleccionista = repository.collectors
    val collectors: MutableLiveData<List<Coleccionista>?> = _coleccionista

    suspend fun fetchCollectors() {
        repository.fetchCollectors()
    }

}
