package com.photovault.security.vault

import java.io.File

class VaultManager {
    private val vaultedFiles = mutableSetOf<String>()

    fun addToVault(filePath: String): Boolean {
        return vaultedFiles.add(filePath)
    }

    fun removeFromVault(filePath: String): Boolean {
        return vaultedFiles.remove(filePath)
    }

    fun isInVault(filePath: String): Boolean {
        return vaultedFiles.contains(filePath)
    }

    fun getVaultedFiles(): Set<String> = vaultedFiles.toSet()

    fun clearVault() {
        vaultedFiles.clear()
    }

    fun exportVaultList(): String {
        return vaultedFiles.joinToString("\n")
    }

    fun importVaultList(content: String) {
        vaultedFiles.clear()
        vaultedFiles.addAll(content.split("\n").filter { it.isNotBlank() })
    }

    fun countVaultedFiles(): Int = vaultedFiles.size
}
