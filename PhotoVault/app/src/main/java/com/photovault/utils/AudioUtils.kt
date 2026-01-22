package com.photovault.utils

object AudioUtils {

    fun formatDuration(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        val hours = (milliseconds / (1000 * 60 * 60))

        return when {
            hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
            else -> String.format("%d:%02d", minutes, seconds)
        }
    }

    fun getAudioBitrate(fileSize: Long, durationMs: Long): Long {
        if (durationMs <= 0) return 0
        val durationSeconds = durationMs / 1000
        return (fileSize * 8) / durationSeconds // in kbps
    }
}
