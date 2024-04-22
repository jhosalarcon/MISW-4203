package com.misw.vinilos_g24.models

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<String>,
    val performers: List<String>,
    val comments: List<String>
)