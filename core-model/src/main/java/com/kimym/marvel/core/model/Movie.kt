package com.kimym.marvel.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val phase: Int,
    val content: String,
    val release: String,
    @ColumnInfo(name = "running_time") val runningTime: Int,
    val image: String
)
