package com.example.recyclerviewstudy

sealed class MovieItems {
    data class ImageMovie(
        val id: Int, val image: Int, val title: String
    ) : MovieItems()

    data class TextMovie(
        val id: Int, val title: String
    ) : MovieItems()
}