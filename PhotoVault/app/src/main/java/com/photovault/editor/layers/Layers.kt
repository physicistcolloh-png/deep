package com.photovault.editor.layers

import java.util.UUID

class AdjustmentLayer(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    var opacity: Float = 100f,
    var blendMode: String = "normal"
) {
    val adjustments = mutableMapOf<String, Any>()

    fun addAdjustment(key: String, value: Any) {
        adjustments[key] = value
    }

    fun removeAdjustment(key: String) {
        adjustments.remove(key)
    }

    fun export(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "opacity" to opacity,
            "blendMode" to blendMode,
            "adjustments" to adjustments.toMap()
        )
    }
}

class MaskLayer(
    val id: String = UUID.randomUUID().toString(),
    val name: String
) {
    private val maskData = mutableListOf<Boolean>() // Simplified mask representation

    fun paintMask(x: Int, y: Int, radius: Int, value: Boolean) {
        // Simplified mask painting
    }

    fun export(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "maskData" to maskData.toList()
        )
    }
}
