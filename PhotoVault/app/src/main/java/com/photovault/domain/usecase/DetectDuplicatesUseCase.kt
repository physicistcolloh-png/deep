package com.photovault.domain.usecase

import com.photovault.data.model.PhotoModel
import com.photovault.data.repository.PhotoRepository

class DetectDuplicatesUseCase(private val photoRepository: PhotoRepository) {

    suspend fun findDuplicates(photoId: String): List<PhotoModel> {
        return photoRepository.findDuplicatePhotos(photoId)
    }

    suspend fun removeDuplicate(photoId: String): Boolean {
        return try {
            photoRepository.deletePhoto(photoId)
            true
        } catch (e: Exception) {
            false
        }
    }
}
