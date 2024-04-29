package com.misw.vinilos_g24.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Artistas"
    }
    val text: LiveData<String> = _text
}