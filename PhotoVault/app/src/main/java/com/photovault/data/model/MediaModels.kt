package com.photovault.data.model

import java.io.File

data class PhotoModel(
    val id: String,
    val name: String,
    val path: String,
    val file: File,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val width: Int = 0,
    val height: Int = 0,
    val isEncrypted: Boolean = false,
    val tags: List<String> = emptyList(),
    val rating: Int = 0,
    val isFavorite: Boolean = false,
    val editHistory: List<String> = emptyList(),
    val checksum: String = "" // For duplicate detection
)

data class AudioModel(
    val id: String,
    val name: String,
    val path: String,
    val file: File,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val duration: Long = 0L, // in milliseconds
    val artist: String = "",
    val album: String = "",
    val isEncrypted: Boolean = false,
    val tags: List<String> = emptyList()
)

data class VideoModel(
    val id: String,
    val name: String,
    val path: String,
    val file: File,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val duration: Long = 0L, // in milliseconds
    val width: Int = 0,
    val height: Int = 0,
    val frameRate: Float = 30f,
    val isEncrypted: Boolean = false,
    val tags: List<String> = emptyList(),
    val isFavorite: Boolean = false
)

data class FileModel(
    val id: String,
    val name: String,
    val path: String,
    val file: File,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long,
    val dateModified: Long,
    val isEncrypted: Boolean = false,
    val isDirectory: Boolean = false,
    val tags: List<String> = emptyList()
)

data class FolderModel(
    val id: String,
    val name: String,
    val path: String,
    val file: File,
    val dateCreated: Long,
    val dateModified: Long,
    val itemCount: Int = 0,
    val isEncrypted: Boolean = false
)
