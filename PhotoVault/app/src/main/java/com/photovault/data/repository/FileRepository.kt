package com.photovault.data.repository

import com.photovault.data.model.FileModel
import com.photovault.data.source.local.FileDataSource

class FileRepository(private val fileDataSource: FileDataSource) {

    fun getFilesFromDirectory(dirPath: String): List<FileModel> {
        return fileDataSource.loadFilesFromDirectory(dirPath)
    }

    fun createFile(parentDir: String, fileName: String, content: ByteArray? = null): FileModel? {
        return fileDataSource.createFile(parentDir, fileName, content)
    }

    fun deleteFile(filePath: String): Boolean {
        return fileDataSource.deleteFile(filePath)
    }

    fun renameFile(oldPath: String, newName: String): Boolean {
        return fileDataSource.renameFile(oldPath, newName)
    }

    fun copyFile(sourcePath: String, destinationDir: String): Boolean {
        return fileDataSource.copyFile(sourcePath, destinationDir)
    }

    fun moveFile(sourcePath: String, destinationDir: String): Boolean {
        return fileDataSource.moveFile(sourcePath, destinationDir)
    }
}
