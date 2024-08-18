package com.kimym.marvel.domain.usecase

import com.kimym.marvel.domain.repository.RatingRepository
import javax.inject.Inject

class UpsertRatingUseCase @Inject constructor(
    private val repository: RatingRepository
) {
    suspend operator fun invoke(id: Int, rating: Float) {
        repository.upsertRating(id, rating)
    }
}
