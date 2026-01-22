package com.photovault.storage.internal

import android.content.Context
import java.io.File

class AppFileManager(private val context: Context) {

    fun getAppCacheDir(): File = context.cacheDir

    fun getAppFilesDir(): File = context.filesDir

    fun getOrCreateAppDir(dirName: String): File {
        val dir = File(context.filesDir, dirName)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    fun saveFileToAppStorage(fileName: String, content: ByteArray): File? {
        return try {
            val file = File(context.filesDir, fileName)
            file.writeBytes(content)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun readFileFromAppStorage(fileName: String): ByteArray? {
        return try {
            val file = File(context.filesDir, fileName)
            if (file.exists()) file.readBytes() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun deleteFileFromAppStorage(fileName: String): Boolean {
        return try {
            File(context.filesDir, fileName).delete()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAllFilesInAppStorage(): List<File> {
        return try {
            context.filesDir.listFiles()?.toList() ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getAppStorageSize(): Long {
        return try {
            context.filesDir.walkTopDown().sumOf { it.length() }
        } catch (e: Exception) {
            e.printStackTrace()
            0L
        }
    }

    fun clearAppStorage(): Boolean {
        return try {
            context.filesDir.deleteRecursively()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
