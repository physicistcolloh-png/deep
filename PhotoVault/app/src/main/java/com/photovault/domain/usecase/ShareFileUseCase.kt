package com.photovault.domain.usecase

import com.photovault.data.model.FileModel
import com.photovault.data.repository.FileRepository
import java.io.File
import java.util.UUID

class ShareFileUseCase(private val fileRepository: FileRepository) {

    fun generateShareLink(filePath: String): String {
        // Generate a local share link (not internet-dependent)
        val file = File(filePath)
        return "photovault://share/${file.name}/${UUID.randomUUID()}"
    }

    fun generateQRCodeContent(filePath: String): String {
        // Generate content for QR code
        return generateShareLink(filePath)
    }

    fun getBluetoothSharePath(filePath: String): String {
        // For Bluetooth sharing, just return the file path
        return filePath
    }

    fun getXenderSharePath(filePath: String): String {
        // For Xender, return the file path
        return filePath
    }

    suspend fun shareMultipleFiles(filePaths: List<String>, destinationDir: String): Boolean {
        return try {
            filePaths.forEach { filePath ->
                fileRepository.copyFile(filePath, destinationDir)
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
