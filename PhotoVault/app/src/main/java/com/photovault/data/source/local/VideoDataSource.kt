package com.photovault.data.source.local

import android.content.Context
import android.provider.MediaStore
import com.photovault.data.model.VideoModel
import java.io.File

class VideoDataSource(private val context: Context) {

    fun loadVideosFromStorage(): List<VideoModel> {
        val videos = mutableListOf<VideoModel>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT
        )

        try {
            val cursor = context.contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                "${MediaStore.Video.Media.DATE_MODIFIED} DESC"
            )

            cursor?.use { c ->
                val idIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val nameIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val pathIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val mimeIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
                val sizeIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                val dateAddedIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
                val dateModifiedIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
                val durationIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
                val widthIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
                val heightIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)

                while (c.moveToNext()) {
                    val file = File(c.getString(pathIndex))
                    if (file.exists()) {
                        videos.add(
                            VideoModel(
                                id = c.getString(idIndex),
                                name = c.getString(nameIndex),
                                path = c.getString(pathIndex),
                                file = file,
                                mimeType = c.getString(mimeIndex) ?: "video/*",
                                size = c.getLong(sizeIndex),
                                dateCreated = c.getLong(dateAddedIndex) * 1000,
                                dateModified = c.getLong(dateModifiedIndex) * 1000,
                                duration = c.getLong(durationIndex),
                                width = c.getInt(widthIndex),
                                height = c.getInt(heightIndex)
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return videos
    }
}
