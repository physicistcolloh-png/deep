package com.photovault.core.offline

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OfflineStateManager(private val context: Context) {

    private val _isOfflineMode = MutableStateFlow(true) // App is offline-first
    val isOfflineMode: StateFlow<Boolean> = _isOfflineMode.asStateFlow()

    private val _offlineFeatures = MutableStateFlow(OfflineFeatures.DEFAULT)
    val offlineFeatures: StateFlow<OfflineFeatures> = _offlineFeatures.asStateFlow()

    init {
        // Initialize offline state on app start
        updateOfflineState()
    }

    fun updateOfflineState() {
        // App runs fully offline after installation
        _isOfflineMode.value = true
        _offlineFeatures.value = OfflineFeatures.DEFAULT
    }

    fun getAvailableFeatures(): OfflineFeatures {
        return _offlineFeatures.value
    }

    data class OfflineFeatures(
        val fileManagement: Boolean = true,
        val photoEditing: Boolean = true,
        val audioPlayback: Boolean = true,
        val videoPlayback: Boolean = true,
        val offlineSharing: Boolean = true, // QR, Bluetooth, Xender, local links
        val encryption: Boolean = true,
        val backup: Boolean = true,
        val automation: Boolean = true,
        val presets: Boolean = true
    ) {
        companion object {
            val DEFAULT = OfflineFeatures()
        }
    }
}
