package com.photovault.storage.analyzer

import java.io.File
import java.security.MessageDigest

class DuplicateScanner {

    fun scanForDuplicates(dirPath: String): Map<String, List<File>> {
        val file = File(dirPath)
        if (!file.isDirectory) return emptyMap()

        val hashMap = mutableMapOf<String, MutableList<File>>()

        file.walkTopDown().forEach { f ->
            if (f.isFile) {
                val hash = calculateFileHash(f)
                if (!hashMap.containsKey(hash)) {
                    hashMap[hash] = mutableListOf()
                }
                hashMap[hash]?.add(f)
            }
        }

        // Return only files that have duplicates
        return hashMap.filter { it.value.size > 1 }
    }

    fun removeDuplicates(duplicateGroups: Map<String, List<File>>, keepFirst: Boolean = true): Int {
        var deletedCount = 0

        duplicateGroups.forEach { (_, files) ->
            val filesToDelete = if (keepFirst) files.drop(1) else files
            filesToDelete.forEach { file ->
                if (file.delete()) {
                    deletedCount++
                }
            }
        }

        return deletedCount
    }

    private fun calculateFileHash(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { fis ->
                val buffer = ByteArray(1024)
                var length: Int
                while (fis.read(buffer).also { length = it } != -1) {
                    digest.update(buffer, 0, length)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            ""
        }
    }
}
