package com.photovault.domain.usecase

import com.photovault.data.model.PresetModel
import com.photovault.data.repository.PresetRepository
import kotlinx.coroutines.flow.Flow

class ApplyEditUseCase(private val presetRepository: PresetRepository) {

    fun getAllPresets(): Flow<List<PresetModel>> {
        return presetRepository.getAllPresets()
    }

    suspend fun applyPreset(preset: PresetModel): Boolean {
        return try {
            presetRepository.savePreset(preset)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun createCustomPreset(
        name: String,
        exposure: Float,
        contrast: Float,
        saturation: Float,
        temperature: Float,
        tint: Float,
        highlights: Float,
        shadows: Float,
        clarity: Float,
        vibrance: Float
    ): PresetModel {
        val preset = PresetModel(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            exposure = exposure,
            contrast = contrast,
            saturation = saturation,
            temperature = temperature,
            tint = tint,
            highlights = highlights,
            shadows = shadows,
            clarity = clarity,
            vibrance = vibrance,
            dateCreated = System.currentTimeMillis(),
            dateModified = System.currentTimeMillis()
        )
        presetRepository.savePreset(preset)
        return preset
    }
}
