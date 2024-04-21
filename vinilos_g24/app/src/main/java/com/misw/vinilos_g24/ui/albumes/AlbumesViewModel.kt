package com.misw.vinilos_g24.ui.albumes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Álbumes"
    }
    val text: LiveData<String> = _text
}