package com.photovault.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val path: String,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val width: Int = 0,
    val height: Int = 0,
    val isEncrypted: Boolean = false,
    val tags: String = "", // comma-separated
    val rating: Int = 0,
    val isFavorite: Boolean = false,
    val checksum: String = ""
)

@Entity(tableName = "audio")
data class AudioEntity(
    @PrimaryKey val id: String,
    val name: String,
    val path: String,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val duration: Long = 0L,
    val artist: String = "",
    val album: String = "",
    val isEncrypted: Boolean = false,
    val tags: String = ""
)

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val path: String,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val duration: Long = 0L,
    val width: Int = 0,
    val height: Int = 0,
    val frameRate: Float = 30f,
    val isEncrypted: Boolean = false,
    val tags: String = "",
    val isFavorite: Boolean = false
)

@Entity(tableName = "edit_steps")
data class EditStepEntity(
    @PrimaryKey val id: String,
    val photoId: String,
    val name: String,
    val type: String,
    val parameters: String = "", // JSON serialized
    val timestamp: Long = 0L
)

@Entity(tableName = "presets")
data class PresetEntity(
    @PrimaryKey val id: String,
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
    val tags: String = "",
    val isFavorite: Boolean = false,
    val dateCreated: Long = 0L,
    val dateModified: Long = 0L
)
