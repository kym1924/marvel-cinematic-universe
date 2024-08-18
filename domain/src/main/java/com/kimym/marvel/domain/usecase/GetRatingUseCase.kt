package com.kimym.marvel.domain.usecase

import com.kimym.marvel.domain.repository.RatingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRatingUseCase @Inject constructor(
    private val repository: RatingRepository
) {
    operator fun invoke(id: Int): Flow<Float> {
        return repository.getRating(id)
    }
}
