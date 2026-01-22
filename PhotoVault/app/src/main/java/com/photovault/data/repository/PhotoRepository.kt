package com.photovault.data.repository

import com.photovault.data.model.PhotoModel
import com.photovault.data.source.local.ImageDataSource
import com.photovault.database.AppDatabase
import com.photovault.database.entities.PhotoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoRepository(
    private val imageDataSource: ImageDataSource,
    private val database: AppDatabase
) {

    suspend fun loadPhotosFromDevice(): List<PhotoModel> {
        val photos = imageDataSource.loadPhotosFromStorage()
        // Cache to database
        photos.forEach { photo ->
            database.photoDao().insertPhoto(
                PhotoEntity(
                    id = photo.id,
                    name = photo.name,
                    path = photo.path,
                    mimeType = photo.mimeType,
                    size = photo.size,
                    dateCreated = photo.dateCreated,
                    dateModified = photo.dateModified,
                    width = photo.width,
                    height = photo.height,
                    checksum = photo.checksum
                )
            )
        }
        return photos
    }

    fun getAllPhotos(): Flow<List<PhotoModel>> {
        return database.photoDao().getAllPhotos().map { entities ->
            entities.map { entity ->
                PhotoModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    width = entity.width,
                    height = entity.height,
                    isEncrypted = entity.isEncrypted,
                    tags = entity.tags.split(",").filter { it.isNotEmpty() },
                    rating = entity.rating,
                    isFavorite = entity.isFavorite,
                    checksum = entity.checksum
                )
            }
        }
    }

    fun getFavoritePhotos(): Flow<List<PhotoModel>> {
        return database.photoDao().getFavoritePhotos().map { entities ->
            entities.map { entity ->
                PhotoModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    width = entity.width,
                    height = entity.height,
                    isFavorite = entity.isFavorite
                )
            }
        }
    }

    suspend fun toggleFavorite(photoId: String) {
        val photo = database.photoDao().getPhotoById(photoId)
        photo?.let {
            database.photoDao().updatePhoto(it.copy(isFavorite = !it.isFavorite))
        }
    }

    suspend fun updateRating(photoId: String, rating: Int) {
        val photo = database.photoDao().getPhotoById(photoId)
        photo?.let {
            database.photoDao().updatePhoto(it.copy(rating = rating))
        }
    }

    suspend fun deletePhoto(photoId: String) {
        database.photoDao().deletePhotoById(photoId)
    }

    suspend fun findDuplicatePhotos(photoId: String): List<PhotoModel> {
        val photo = database.photoDao().getPhotoById(photoId)
        return if (photo != null && photo.checksum.isNotEmpty()) {
            database.photoDao().findDuplicates(photo.checksum, photoId).map { entity ->
                PhotoModel(
                    id = entity.id,
                    name = entity.name,
                    path = entity.path,
                    file = java.io.File(entity.path),
                    mimeType = entity.mimeType,
                    size = entity.size,
                    dateCreated = entity.dateCreated,
                    dateModified = entity.dateModified,
                    width = entity.width,
                    height = entity.height
                )
            }
        } else {
            emptyList()
        }
    }
}
