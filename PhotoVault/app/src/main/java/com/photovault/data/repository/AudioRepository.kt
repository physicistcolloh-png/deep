package com.photovault.data.repository

import com.photovault.data.model.AudioModel
import com.photovault.data.source.local.AudioDataSource
import com.photovault.database.AppDatabase
import com.photovault.database.entities.AudioEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AudioRepository(
    private val audioDataSource: AudioDataSource,
    private val database: AppDatabase
) {

    suspend fun loadAudioFromDevice(): List<AudioModel> {
        val audioList = audioDataSource.loadAudioFromStorage()
        audioList.forEach { audio ->
            database.audioDao().insertAudio(
                AudioEntity(
                    id = audio.id,
                    name = audio.name,
                    path = audio.path,
                    mimeType = audio.mimeType,
                    size = audio.size,
                    dateCreated = audio.dateCreated,
                    dateModified = audio.dateModified,
                    duration = audio.duration,
                    artist = audio.artist,
                    album = audio.album
                )
            )
        }
        return audioList
    }

    fun getAllAudio(): Flow<List<AudioModel>> {
        return database.audioDao().getAllAudio().map { entities ->
            entities.map { entity ->
                AudioModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    duration = entity.duration,
                    artist = entity.artist,
                    album = entity.album
                )
            }
        }
    }

    fun getAudioByArtist(artist: String): Flow<List<AudioModel>> {
        return database.audioDao().getAudioByArtist(artist).map { entities ->
            entities.map { entity ->
                AudioModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    duration = entity.duration
                )
            }
        }
    }

    suspend fun deleteAudio(audioId: String) {
        database.audioDao().deleteAudioById(audioId)
    }
}
