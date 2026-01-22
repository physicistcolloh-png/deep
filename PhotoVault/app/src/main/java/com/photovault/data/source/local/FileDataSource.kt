package com.photovault.data.source.local

import android.content.Context
import com.photovault.data.model.FileModel
import java.io.File
import java.util.UUID

class FileDataSource(private val context: Context) {

    fun loadFilesFromDirectory(dirPath: String): List<FileModel> {
        val files = mutableListOf<FileModel>()
        val directory = File(dirPath)

        if (!directory.exists() || !directory.isDirectory) {
            return files
        }

        try {
            directory.listFiles()?.forEach { file ->
                files.add(
                    FileModel(
                        id = UUID.randomUUID().toString(),
                        name = file.name,
                        path = file.absolutePath,
                        file = file,
                        mimeType = getMimeType(file),
                        size = file.length(),
                        dateCreated = file.lastModified(),
                        dateModified = file.lastModified(),
                        isDirectory = file.isDirectory
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return files
    }

    fun createFile(parentDir: String, fileName: String, content: ByteArray? = null): FileModel? {
        return try {
            val file = File(parentDir, fileName)
            if (file.createNewFile()) {
                content?.let { file.writeBytes(it) }
                FileModel(
                    id = UUID.randomUUID().toString(),
                    name = file.name,
                    path = file.absolutePath,
                    file = file,
                    mimeType = getMimeType(file),
                    size = file.length(),
                    dateCreated = file.lastModified(),
                    dateModified = file.lastModified(),
                    isDirectory = false
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun deleteFile(filePath: String): Boolean {
        return try {
            File(filePath).delete()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun renameFile(oldPath: String, newName: String): Boolean {
        return try {
            val oldFile = File(oldPath)
            val newFile = File(oldFile.parent, newName)
            oldFile.renameTo(newFile)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun copyFile(sourcePath: String, destinationDir: String): Boolean {
        return try {
            val sourceFile = File(sourcePath)
            val destFile = File(destinationDir, sourceFile.name)
            sourceFile.copyTo(destFile, overwrite = true)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun moveFile(sourcePath: String, destinationDir: String): Boolean {
        return try {
            val sourceFile = File(sourcePath)
            val destFile = File(destinationDir, sourceFile.name)
            sourceFile.renameTo(destFile)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun getMimeType(file: File): String {
        return when (file.extension.lowercase()) {
            "pdf" -> "application/pdf"
            "doc", "docx" -> "application/msword"
            "xls", "xlsx" -> "application/vnd.ms-excel"
            "ppt", "pptx" -> "application/vnd.ms-powerpoint"
            "txt" -> "text/plain"
            "json" -> "application/json"
            "zip" -> "application/zip"
            "rar" -> "application/x-rar-compressed"
            else -> "application/octet-stream"
        }
    }
}
