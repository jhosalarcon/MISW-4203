package com.misw.vinilos_g24.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.repositories.AlbumRepository

class AlbumesViewModel (application: Application)  : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Álbumes"
    }
    val text: LiveData<String> = _text

    private val albumsRepository = AlbumRepository(application)

}