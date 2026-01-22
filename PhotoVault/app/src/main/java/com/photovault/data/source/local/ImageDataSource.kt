package com.photovault.data.source.local

import android.content.Context
import android.provider.MediaStore
import com.photovault.data.model.PhotoModel
import java.io.File
import java.security.MessageDigest

class ImageDataSource(private val context: Context) {

    fun loadPhotosFromStorage(): List<PhotoModel> {
        val photos = mutableListOf<PhotoModel>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )

        try {
            val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
            )

            cursor?.use { c ->
                val idIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val pathIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val mimeIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
                val sizeIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateAddedIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
                val dateModifiedIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                val widthIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while (c.moveToNext()) {
                    val id = c.getString(idIndex)
                    val name = c.getString(nameIndex)
                    val path = c.getString(pathIndex)
                    val mimeType = c.getString(mimeIndex) ?: "image/*"
                    val size = c.getLong(sizeIndex)
                    val dateCreated = c.getLong(dateAddedIndex) * 1000
                    val dateModified = c.getLong(dateModifiedIndex) * 1000
                    val width = c.getInt(widthIndex)
                    val height = c.getInt(heightIndex)

                    val file = File(path)
                    if (file.exists()) {
                        photos.add(
                            PhotoModel(
                                id = id,
                                name = name,
                                path = path,
                                file = file,
                                mimeType = mimeType,
                                size = size,
                                dateCreated = dateCreated,
                                dateModified = dateModified,
                                width = width,
                                height = height,
                                checksum = calculateChecksum(file)
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return photos
    }

    private fun calculateChecksum(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { fis ->
                val buffer = ByteArray(1024)
                var length: Int
                while (fis.read(buffer).also { length = it } != -1) {
                    digest.update(buffer, 0, length)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            ""
        }
    }
}
