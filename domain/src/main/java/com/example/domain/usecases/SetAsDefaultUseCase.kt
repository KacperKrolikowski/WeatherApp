package com.example.domain.usecases

import com.example.domain.repositories.SharedPreferenceRepository
import javax.inject.Inject

class SetAsDefaultUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {
    fun execute(id: String) {
        sharedPreferenceRepository.savedPlaceId = id
    }
}