package com.photovault.storage.external

import android.content.Context
import android.os.Environment
import java.io.File

class ExternalFileManager(private val context: Context) {

    fun getExternalStorageDir(): File? {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStorageDirectory()
        } else {
            null
        }
    }

    fun getPublicDocumentsDir(): File? {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "PhotoVault")
                .also { it.mkdirs() }
        } else {
            null
        }
    }

    fun getPublicPicturesDir(): File? {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "PhotoVault")
                .also { it.mkdirs() }
        } else {
            null
        }
    }

    fun getPublicDownloadsDir(): File? {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "PhotoVault")
                .also { it.mkdirs() }
        } else {
            null
        }
    }

    fun isExternalStorageAvailable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun isExternalStorageReadOnly(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY
    }

    fun getExternalStorageSize(): Long {
        return try {
            getExternalStorageDir()?.walkTopDown()?.sumOf { it.length() } ?: 0L
        } catch (e: Exception) {
            e.printStackTrace()
            0L
        }
    }
}
