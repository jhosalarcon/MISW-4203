package com.misw.vinilos_g24.models

data class PostData(
    val rating: Int,
    val description: String,
    val collector: Int = 1 // Valor predeterminado en caso de no proporcionarse
)