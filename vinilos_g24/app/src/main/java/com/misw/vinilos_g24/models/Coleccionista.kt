package com.misw.vinilos_g24.models

import java.util.Date

data class Coleccionista(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val birthDate: Date,
    val comments: List<String>
)