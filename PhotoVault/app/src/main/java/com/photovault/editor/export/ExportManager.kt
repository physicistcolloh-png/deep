package com.photovault.editor.export

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

class ExportManager {

    fun exportAsJPEG(bitmap: Bitmap, file: File, quality: Int = 90): Boolean {
        return try {
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun exportAsPNG(bitmap: Bitmap, file: File): Boolean {
        return try {
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun exportAsWebP(bitmap: Bitmap, file: File, quality: Int = 80): Boolean {
        return try {
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.WEBP, quality, fos)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getEstimatedFileSize(bitmap: Bitmap, format: Bitmap.CompressFormat): Long {
        // Rough estimation
        return when (format) {
            Bitmap.CompressFormat.JPEG -> (bitmap.width * bitmap.height * 0.5).toLong()
            Bitmap.CompressFormat.PNG -> (bitmap.width * bitmap.height * 1.5).toLong()
            Bitmap.CompressFormat.WEBP -> (bitmap.width * bitmap.height * 0.8).toLong()
            else -> 0L
        }
    }
}
