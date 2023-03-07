package com.example.domain.usecases

import com.example.domain.repositories.SharedPreferenceRepository
import javax.inject.Inject

class CheckSavedUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {
    fun execute() = sharedPreferenceRepository.savedPlaceId
}