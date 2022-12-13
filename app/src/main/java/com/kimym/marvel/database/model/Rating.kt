package com.kimym.marvel.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val rating: Float
)
