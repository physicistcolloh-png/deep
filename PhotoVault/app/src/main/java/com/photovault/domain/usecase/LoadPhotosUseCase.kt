package com.photovault.domain.usecase

import com.photovault.data.model.PhotoModel
import com.photovault.data.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class LoadPhotosUseCase(private val photoRepository: PhotoRepository) {

    fun execute(): Flow<List<PhotoModel>> {
        return photoRepository.getAllPhotos()
    }

    suspend fun loadFromDevice(): List<PhotoModel> {
        return photoRepository.loadPhotosFromDevice()
    }

    fun getFavorites(): Flow<List<PhotoModel>> {
        return photoRepository.getFavoritePhotos()
    }
}
