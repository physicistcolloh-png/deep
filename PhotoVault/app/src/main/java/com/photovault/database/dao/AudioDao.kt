package com.photovault.database.dao

import androidx.room.*
import com.photovault.database.entities.AudioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {
    @Query("SELECT * FROM audio")
    fun getAllAudio(): Flow<List<AudioEntity>>

    @Query("SELECT * FROM audio WHERE id = :id")
    suspend fun getAudioById(id: String): AudioEntity?

    @Query("SELECT * FROM audio WHERE artist = :artist")
    fun getAudioByArtist(artist: String): Flow<List<AudioEntity>>

    @Query("SELECT * FROM audio WHERE album = :album")
    fun getAudioByAlbum(album: String): Flow<List<AudioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudio(audio: AudioEntity)

    @Update
    suspend fun updateAudio(audio: AudioEntity)

    @Delete
    suspend fun deleteAudio(audio: AudioEntity)

    @Query("DELETE FROM audio WHERE id = :id")
    suspend fun deleteAudioById(id: String)
}
