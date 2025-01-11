package com.kimym.marvel.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import com.kimym.marvel.core.database.entity.MovieAndRatingView.Companion.QUERY

@DatabaseView(
    value = QUERY,
    viewName = "MovieAndRating"
)
data class MovieAndRatingView(
    val id: Int,
    val title: String,
    val image: String,
    val rating: Float,
    @ColumnInfo("created_at") val createdAt: Long
) {
    companion object {
        const val QUERY = "SELECT e.id, e.title, e.image, r.rating, r.created_at " +
            "FROM Movie AS e, Rating AS r " +
            "WHERE e.id = r.id AND r.rating != 0"
    }
}
