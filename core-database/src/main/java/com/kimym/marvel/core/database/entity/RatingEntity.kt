package com.kimym.marvel.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Rating")
data class RatingEntity(
    @PrimaryKey val id: Int,
    val rating: Float,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)
