package com.photovault.data.repository

import com.photovault.database.AppDatabase
import com.photovault.database.entities.PresetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.photovault.data.model.PresetModel

class PresetRepository(private val database: AppDatabase) {

    fun getAllPresets(): Flow<List<PresetModel>> {
        return database.presetDao().getAllPresets().map { entities ->
            entities.map { entity ->
                PresetModel(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description,
                    exposure = entity.exposure,
                    contrast = entity.contrast,
                    saturation = entity.saturation,
                    temperature = entity.temperature,
                    tint = entity.tint,
                    highlights = entity.highlights,
                    shadows = entity.shadows,
                    clarity = entity.clarity,
                    vibrance = entity.vibrance,
                    isFavorite = entity.isFavorite,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified
                )
            }
        }
    }

    fun getFavoritePresets(): Flow<List<PresetModel>> {
        return database.presetDao().getFavoritePresets().map { entities ->
            entities.map { entity ->
                PresetModel(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description,
                    exposure = entity.exposure,
                    contrast = entity.contrast,
                    saturation = entity.saturation,
                    temperature = entity.temperature,
                    tint = entity.tint,
                    highlights = entity.highlights,
                    shadows = entity.shadows,
                    clarity = entity.clarity,
                    vibrance = entity.vibrance,
                    isFavorite = entity.isFavorite
                )
            }
        }
    }

    suspend fun savePreset(preset: PresetModel) {
        database.presetDao().insertPreset(
            PresetEntity(
                id = preset.id,
                name = preset.name,
                description = preset.description,
                exposure = preset.exposure,
                contrast = preset.contrast,
                saturation = preset.saturation,
                temperature = preset.temperature,
                tint = preset.tint,
                highlights = preset.highlights,
                shadows = preset.shadows,
                clarity = preset.clarity,
                vibrance = preset.vibrance,
                isFavorite = preset.isFavorite,
                dateCreated = preset.dateCreated,
                dateModified = preset.dateModified
            )
        )
    }

    suspend fun deletePreset(presetId: String) {
        database.presetDao().deletePresetById(presetId)
    }
}
