package com.photovault.data.source.local

import android.content.Context
import android.provider.MediaStore
import com.photovault.data.model.AudioModel
import java.io.File

class AudioDataSource(private val context: Context) {

    fun loadAudioFromStorage(): List<AudioModel> {
        val audioList = mutableListOf<AudioModel>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM
        )

        try {
            val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                "${MediaStore.Audio.Media.DATE_MODIFIED} DESC"
            )

            cursor?.use { c ->
                val idIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val nameIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val pathIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                val mimeIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE)
                val sizeIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
                val dateAddedIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED)
                val dateModifiedIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_MODIFIED)
                val durationIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val artistIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)

                while (c.moveToNext()) {
                    val file = File(c.getString(pathIndex))
                    if (file.exists()) {
                        audioList.add(
                            AudioModel(
                                id = c.getString(idIndex),
                                name = c.getString(nameIndex),
                                path = c.getString(pathIndex),
                                file = file,
                                mimeType = c.getString(mimeIndex) ?: "audio/*",
                                size = c.getLong(sizeIndex),
                                dateCreated = c.getLong(dateAddedIndex) * 1000,
                                dateModified = c.getLong(dateModifiedIndex) * 1000,
                                duration = c.getLong(durationIndex),
                                artist = c.getString(artistIndex) ?: "",
                                album = c.getString(albumIndex) ?: ""
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return audioList
    }
}
