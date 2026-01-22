package com.photovault.automation.actions

import java.io.File
import java.util.UUID

data class MoveFileAction(
    val id: String = UUID.randomUUID().toString(),
    val sourcePattern: String,
    val destinationDir: String
) {
    fun execute(filePath: String): Boolean {
        return try {
            val file = File(filePath)
            val destFile = File(destinationDir, file.name)
            file.renameTo(destFile)
        } catch (e: Exception) {
            false
        }
    }
}

data class DeleteFileAction(
    val id: String = UUID.randomUUID().toString(),
    val filePattern: String
) {
    fun execute(filePath: String): Boolean {
        return try {
            File(filePath).delete()
        } catch (e: Exception) {
            false
        }
    }
}
