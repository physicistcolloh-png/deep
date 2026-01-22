package com.photovault.utils

import android.graphics.Bitmap
import java.io.File

object FileUtils {

    fun getFileExtension(fileName: String): String {
        val lastDot = fileName.lastIndexOf('.')
        return if (lastDot > 0) fileName.substring(lastDot + 1) else ""
    }

    fun getFileNameWithoutExtension(fileName: String): String {
        val lastDot = fileName.lastIndexOf('.')
        return if (lastDot > 0) fileName.substring(0, lastDot) else fileName
    }

    fun formatFileSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }

    fun isValidFileName(fileName: String): Boolean {
        val invalidChars = arrayOf('/', '\\', ':', '*', '?', '"', '<', '>', '|')
        return !invalidChars.any { fileName.contains(it) }
    }

    fun sanitizeFileName(fileName: String): String {
        val invalidChars = arrayOf('/', '\\', ':', '*', '?', '"', '<', '>', '|')
        var sanitized = fileName
        invalidChars.forEach { char ->
            sanitized = sanitized.replace(char, '_')
        }
        return sanitized
    }

    fun generateUniqueFileName(baseFileName: String, directory: File): String {
        val ext = getFileExtension(baseFileName)
        val name = getFileNameWithoutExtension(baseFileName)
        var counter = 1
        var newFileName = baseFileName

        while (File(directory, newFileName).exists()) {
            newFileName = if (ext.isNotEmpty()) {
                "$name ($counter).$ext"
            } else {
                "$name ($counter)"
            }
            counter++
        }

        return newFileName
    }
}
