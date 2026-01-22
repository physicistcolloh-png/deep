package com.photovault.core.settings

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesManager(private val context: Context) {

    companion object {
        private const val PREFERENCES_NAME = "photovault_preferences"
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val AUTO_BACKUP = booleanPreferencesKey("auto_backup")
        private val ENCRYPTION_ENABLED = booleanPreferencesKey("encryption_enabled")
        private val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
        private val LAST_BACKUP_TIME = longPreferencesKey("last_backup_time")
        private val STORAGE_LOCATION = stringPreferencesKey("storage_location")
        private val SORT_ORDER = stringPreferencesKey("sort_order")
        private val SHOW_HIDDEN_FILES = booleanPreferencesKey("show_hidden_files")
    }

    private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

    suspend fun setThemeMode(mode: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE] = mode
        }
    }

    fun getThemeMode(): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_MODE] ?: "system"
    }

    suspend fun setAutoBackup(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AUTO_BACKUP] = enabled
        }
    }

    fun getAutoBackup(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[AUTO_BACKUP] ?: true
    }

    suspend fun setEncryptionEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ENCRYPTION_ENABLED] = enabled
        }
    }

    fun getEncryptionEnabled(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[ENCRYPTION_ENABLED] ?: true
    }

    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[BIOMETRIC_ENABLED] = enabled
        }
    }

    fun getBiometricEnabled(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[BIOMETRIC_ENABLED] ?: true
    }

    suspend fun setLastBackupTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[LAST_BACKUP_TIME] = time
        }
    }

    fun getLastBackupTime(): Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[LAST_BACKUP_TIME] ?: 0L
    }

    suspend fun setStorageLocation(path: String) {
        context.dataStore.edit { preferences ->
            preferences[STORAGE_LOCATION] = path
        }
    }

    fun getStorageLocation(): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STORAGE_LOCATION] ?: ""
    }

    suspend fun setSortOrder(order: String) {
        context.dataStore.edit { preferences ->
            preferences[SORT_ORDER] = order
        }
    }

    fun getSortOrder(): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SORT_ORDER] ?: "date_desc"
    }

    suspend fun setShowHiddenFiles(show: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_HIDDEN_FILES] = show
        }
    }

    fun getShowHiddenFiles(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[SHOW_HIDDEN_FILES] ?: false
    }
}
