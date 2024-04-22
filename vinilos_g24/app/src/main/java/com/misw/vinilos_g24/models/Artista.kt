package com.misw.vinilos_g24.models

import java.util.Date

data class Artista(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date
)