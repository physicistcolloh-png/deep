package com.photovault.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.photovault.database.dao.AudioDao
import com.photovault.database.dao.PhotoDao
import com.photovault.database.dao.PresetDao
import com.photovault.database.dao.VideoDao
import com.photovault.database.entities.AudioEntity
import com.photovault.database.entities.EditStepEntity
import com.photovault.database.entities.PhotoEntity
import com.photovault.database.entities.PresetEntity
import com.photovault.database.entities.VideoEntity

@Database(
    entities = [
        PhotoEntity::class,
        AudioEntity::class,
        VideoEntity::class,
        EditStepEntity::class,
        PresetEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
    abstract fun audioDao(): AudioDao
    abstract fun videoDao(): VideoDao
    abstract fun presetDao(): PresetDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "photovault.db"
                ).build().also { instance = it }
            }
        }
    }
}
