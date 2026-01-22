package com.photovault.domain.usecase

import com.photovault.data.model.VideoModel
import com.photovault.data.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class LoadVideoUseCase(private val videoRepository: VideoRepository) {

    fun execute(): Flow<List<VideoModel>> {
        return videoRepository.getAllVideos()
    }

    suspend fun loadFromDevice(): List<VideoModel> {
        return videoRepository.loadVideosFromDevice()
    }

    fun getFavorites(): Flow<List<VideoModel>> {
        return videoRepository.getFavoriteVideos()
    }
}
