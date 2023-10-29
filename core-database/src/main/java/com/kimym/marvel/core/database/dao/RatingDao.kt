package com.kimym.marvel.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kimym.marvel.core.database.entity.RatingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {
    @Query("SELECT rating FROM Rating WHERE id = :id")
    fun getRating(id: Int): Flow<Float>

    @Insert
    suspend fun insertRating(rating: RatingEntity)

    @Query("UPDATE Rating SET rating = :rating WHERE id = :id")
    suspend fun updateRating(id: Int, rating: Float)

    @Query("DELETE FROM Rating WHERE id = :id")
    suspend fun deleteRating(id: Int)
}
