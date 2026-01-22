package com.photovault.domain.usecase

import com.photovault.data.model.AudioModel
import com.photovault.data.repository.AudioRepository
import kotlinx.coroutines.flow.Flow

class LoadAudioUseCase(private val audioRepository: AudioRepository) {

    fun execute(): Flow<List<AudioModel>> {
        return audioRepository.getAllAudio()
    }

    suspend fun loadFromDevice(): List<AudioModel> {
        return audioRepository.loadAudioFromDevice()
    }

    fun getByArtist(artist: String): Flow<List<AudioModel>> {
        return audioRepository.getAudioByArtist(artist)
    }
}
