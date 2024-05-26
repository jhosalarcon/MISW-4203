package com.misw.vinilos_g24.models

import java.util.Date

data class Coleccionista(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val birthDate: Date?,
    val comments: List<Comment>,
    val favoritePerformers: List<Any>,
    val collectorAlbums: List<Any>
)

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int
)