package com.kimym.marvel.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val content: String,
    val release: String,
    val runningTime: Int,
    val image: String
)
