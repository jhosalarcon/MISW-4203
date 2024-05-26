package com.misw.vinilos_g24.viewmodels

import ColeccionistaRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misw.vinilos_g24.models.Coleccionista


class ColeccionistaDetailViewModel(private val repository: ColeccionistaRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Detalle de coleccionista"
    }
    val text: LiveData<String> = _text

    private val _collectorDetails = repository.collectorDetails
    val collectorDetails: MutableLiveData<Coleccionista?> = _collectorDetails
    suspend fun fetchCollectorDetails(collectorId: Int) {
        repository.fetchCollectorDetails(collectorId)
    }

}