package com.photovault.domain.usecase

import com.photovault.data.model.FileModel
import com.photovault.data.repository.FileRepository

class BatchEditUseCase(private val fileRepository: FileRepository) {

    suspend fun renameMultipleFiles(
        files: List<FileModel>,
        newNamePattern: String
    ): Pair<Int, Int> {
        var success = 0
        var failed = 0

        files.forEachIndexed { index, file ->
            val newName = newNamePattern.replace("{index}", (index + 1).toString())
            if (fileRepository.renameFile(file.path, newName)) {
                success++
            } else {
                failed++
            }
        }

        return Pair(success, failed)
    }

    suspend fun moveMultipleFiles(
        files: List<FileModel>,
        destinationDir: String
    ): Pair<Int, Int> {
        var success = 0
        var failed = 0

        files.forEach { file ->
            if (fileRepository.moveFile(file.path, destinationDir)) {
                success++
            } else {
                failed++
            }
        }

        return Pair(success, failed)
    }

    suspend fun deleteMultipleFiles(files: List<FileModel>): Pair<Int, Int> {
        var success = 0
        var failed = 0

        files.forEach { file ->
            if (fileRepository.deleteFile(file.path)) {
                success++
            } else {
                failed++
            }
        }

        return Pair(success, failed)
    }
}
