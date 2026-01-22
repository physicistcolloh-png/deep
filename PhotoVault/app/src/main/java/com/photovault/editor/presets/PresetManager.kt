package com.photovault.editor.presets

import com.photovault.data.model.PresetModel
import java.util.UUID

class PresetManager {
    private val presets = mutableListOf<PresetModel>()

    fun createPreset(
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
            id = UUID.randomUUID().toString(),
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
        presets.add(preset)
        return preset
    }

    fun savePreset(preset: PresetModel) {
        val index = presets.indexOfFirst { it.id == preset.id }
        if (index >= 0) {
            presets[index] = preset.copy(dateModified = System.currentTimeMillis())
        } else {
            presets.add(preset.copy(dateCreated = System.currentTimeMillis()))
        }
    }

    fun deletePreset(presetId: String): Boolean {
        return presets.removeAll { it.id == presetId }
    }

    fun getPreset(presetId: String): PresetModel? {
        return presets.find { it.id == presetId }
    }

    fun getAllPresets(): List<PresetModel> = presets.toList()

    fun duplicatePreset(presetId: String): PresetModel? {
        val preset = getPreset(presetId) ?: return null
        val newPreset = preset.copy(
            id = UUID.randomUUID().toString(),
            name = "${preset.name} (Copy)",
            dateCreated = System.currentTimeMillis()
        )
        savePreset(newPreset)
        return newPreset
    }
}
