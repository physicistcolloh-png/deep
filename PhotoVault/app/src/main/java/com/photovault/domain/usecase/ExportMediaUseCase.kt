package com.photovault.domain.usecase

import com.photovault.data.model.FileModel
import com.photovault.data.repository.FileRepository

class ExportMediaUseCase(private val fileRepository: FileRepository) {

    suspend fun exportMedia(
        sourceFile: FileModel,
        destinationDir: String,
        format: String? = null
    ): Boolean {
        return try {
            // In a real app, this would handle format conversion if needed
            fileRepository.copyFile(sourceFile.path, destinationDir)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun exportBatch(
        sourceFiles: List<FileModel>,
        destinationDir: String
    ): Pair<Int, Int> {
        var success = 0
        var failed = 0

        sourceFiles.forEach { file ->
            if (exportMedia(file, destinationDir)) {
                success++
            } else {
                failed++
            }
        }

        return Pair(success, failed)
    }
}
