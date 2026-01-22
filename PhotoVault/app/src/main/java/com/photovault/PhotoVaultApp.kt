package com.photovault

import android.app.Application
import com.photovault.core.dispatcher.DefaultAppDispatchers
import com.photovault.core.offline.OfflineStateManager
import com.photovault.core.permissions.MediaPermissionManager
import com.photovault.core.permissions.StoragePermissionManager
import com.photovault.core.settings.PreferencesManager
import com.photovault.database.AppDatabase
import com.photovault.data.source.local.AudioDataSource
import com.photovault.data.source.local.FileDataSource
import com.photovault.data.source.local.ImageDataSource
import com.photovault.data.source.local.VideoDataSource
import com.photovault.data.repository.AudioRepository
import com.photovault.data.repository.FileRepository
import com.photovault.data.repository.PhotoRepository
import com.photovault.data.repository.PresetRepository
import com.photovault.data.repository.VideoRepository

class PhotoVaultApp : Application() {

    // Core
    lateinit var appDispatchers: DefaultAppDispatchers
    lateinit var offlineStateManager: OfflineStateManager
    lateinit var preferencesManager: PreferencesManager
    lateinit var storagePermissionManager: StoragePermissionManager
    lateinit var mediaPermissionManager: MediaPermissionManager

    // Database
    lateinit var database: AppDatabase

    // Data Sources
    lateinit var imageDataSource: ImageDataSource
    lateinit var audioDataSource: AudioDataSource
    lateinit var videoDataSource: VideoDataSource
    lateinit var fileDataSource: FileDataSource

    // Repositories
    lateinit var photoRepository: PhotoRepository
    lateinit var audioRepository: AudioRepository
    lateinit var videoRepository: VideoRepository
    lateinit var fileRepository: FileRepository
    lateinit var presetRepository: PresetRepository

    override fun onCreate() {
        super.onCreate()

        // Initialize core modules
        appDispatchers = DefaultAppDispatchers()
        offlineStateManager = OfflineStateManager(this)
        preferencesManager = PreferencesManager(this)
        storagePermissionManager = StoragePermissionManager(this)
        mediaPermissionManager = MediaPermissionManager(this)

        // Initialize database
        database = AppDatabase.getInstance(this)

        // Initialize data sources
        imageDataSource = ImageDataSource(this)
        audioDataSource = AudioDataSource(this)
        videoDataSource = VideoDataSource(this)
        fileDataSource = FileDataSource(this)

        // Initialize repositories
        photoRepository = PhotoRepository(imageDataSource, database)
        audioRepository = AudioRepository(audioDataSource, database)
        videoRepository = VideoRepository(videoDataSource, database)
        fileRepository = FileRepository(fileDataSource)
        presetRepository = PresetRepository(database)
    }
}
