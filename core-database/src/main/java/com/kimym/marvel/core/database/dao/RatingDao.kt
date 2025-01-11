package com.kimym.marvel.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kimym.marvel.core.database.entity.RatingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {
    @Query("SELECT rating FROM Rating WHERE id = :id")
    fun getRating(id: Int): Flow<Float>

    @Upsert
    suspend fun upsertRating(rating: RatingEntity)
}
