package com.photovault.data.model

data class PresetModel(
    val id: String,
    val name: String,
    val description: String = "",
    val exposure: Float = 0f,
    val contrast: Float = 0f,
    val saturation: Float = 0f,
    val temperature: Float = 0f,
    val tint: Float = 0f,
    val highlights: Float = 0f,
    val shadows: Float = 0f,
    val clarity: Float = 0f,
    val vibrance: Float = 0f,
    val tags: List<String> = emptyList(),
    val isFavorite: Boolean = false,
    val dateCreated: Long = 0L,
    val dateModified: Long = 0L
)

data class EditStackModel(
    val id: String,
    val photoId: String,
    val originalPath: String,
    val steps: List<EditStep> = emptyList(),
    val lastModified: Long = 0L,
    val isSaved: Boolean = false
)

data class EditStep(
    val id: String,
    val name: String,
    val type: String, // "exposure", "curves", "hsl", "crop", etc.
    val parameters: Map<String, Any> = emptyMap(),
    val timestamp: Long = 0L
)
