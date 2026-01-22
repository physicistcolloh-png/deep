package com.photovault.storage.analyzer

import java.io.File

class StorageAnalyzer {

    fun analyzeDirectory(dirPath: String): DirectoryAnalysis {
        val file = File(dirPath)
        if (!file.isDirectory) return DirectoryAnalysis()

        var totalSize = 0L
        var fileCount = 0
        var folderCount = 0
        val fileTypeMap = mutableMapOf<String, Long>()

        file.walkTopDown().forEach { f ->
            if (f.isFile) {
                fileCount++
                totalSize += f.length()

                val ext = f.extension.ifEmpty { "no-extension" }
                fileTypeMap[ext] = (fileTypeMap[ext] ?: 0L) + f.length()
            } else if (f.isDirectory && f != file) {
                folderCount++
            }
        }

        return DirectoryAnalysis(
            totalSize = totalSize,
            fileCount = fileCount,
            folderCount = folderCount,
            fileTypeMap = fileTypeMap
        )
    }

    data class DirectoryAnalysis(
        val totalSize: Long = 0L,
        val fileCount: Int = 0,
        val folderCount: Int = 0,
        val fileTypeMap: Map<String, Long> = emptyMap()
    )
}
