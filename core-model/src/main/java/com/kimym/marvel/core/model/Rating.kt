package com.kimym.marvel.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey val id: Int,
    val rating: Float,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)
