package com.photovault.data.repository

import com.photovault.data.model.VideoModel
import com.photovault.data.source.local.VideoDataSource
import com.photovault.database.AppDatabase
import com.photovault.database.entities.VideoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoRepository(
    private val videoDataSource: VideoDataSource,
    private val database: AppDatabase
) {

    suspend fun loadVideosFromDevice(): List<VideoModel> {
        val videos = videoDataSource.loadVideosFromStorage()
        videos.forEach { video ->
            database.videoDao().insertVideo(
                VideoEntity(
                    id = video.id,
                    name = video.name,
                    path = video.path,
                    mimeType = video.mimeType,
                    size = video.size,
                    dateCreated = video.dateCreated,
                    dateModified = video.dateModified,
                    duration = video.duration,
                    width = video.width,
                    height = video.height
                )
            )
        }
        return videos
    }

    fun getAllVideos(): Flow<List<VideoModel>> {
        return database.videoDao().getAllVideos().map { entities ->
            entities.map { entity ->
                VideoModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    duration = entity.duration,
                    width = entity.width,
                    height = entity.height
                )
            }
        }
    }

    fun getFavoriteVideos(): Flow<List<VideoModel>> {
        return database.videoDao().getFavoriteVideos().map { entities ->
            entities.map { entity ->
                VideoModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    duration = entity.duration,
                    width = entity.width,
                    height = entity.height,
                    isFavorite = entity.isFavorite
                )
            }
        }
    }

    suspend fun toggleFavorite(videoId: String) {
        val video = database.videoDao().getVideoById(videoId)
        video?.let {
            database.videoDao().updateVideo(it.copy(isFavorite = !it.isFavorite))
        }
    }

    suspend fun deleteVideo(videoId: String) {
        database.videoDao().deleteVideoById(videoId)
    }
}
