package com.kimym.marvel.core.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.kimym.marvel.core.model.MovieAndRating.Companion.QUERY

@DatabaseView(QUERY)
data class MovieAndRating(
    val id: Int,
    val title: String,
    val image: String,
    val rating: Float,
    @ColumnInfo("created_at") val createdAt: Long
) {
    companion object {
        const val QUERY = "SELECT e.id, e.title, e.image, r.rating, r.created_at " +
            "FROM Movie AS e, Rating AS r " +
            "WHERE e.id = r.id"
    }
}
