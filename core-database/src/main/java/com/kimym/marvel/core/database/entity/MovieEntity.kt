package com.kimym.marvel.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Movie")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val phase: Int,
    val content: String,
    val release: String,
    @ColumnInfo(name = "running_time") val runningTime: Int,
    val image: String
)
