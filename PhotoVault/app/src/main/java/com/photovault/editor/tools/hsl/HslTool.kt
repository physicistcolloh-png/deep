package com.photovault.editor.tools.hsl

class HslTool {
    var hue: Float = 0f
    var saturation: Float = 0f
    var lightness: Float = 0f

    fun applyHue(value: Float) {
        this.hue = value.coerceIn(-180f, 180f)
    }

    fun applySaturation(value: Float) {
        this.saturation = value.coerceIn(-100f, 100f)
    }

    fun applyLightness(value: Float) {
        this.lightness = value.coerceIn(-100f, 100f)
    }

    fun reset() {
        hue = 0f
        saturation = 0f
        lightness = 0f
    }

    fun export(): Map<String, Float> {
        return mapOf(
            "hue" to hue,
            "saturation" to saturation,
            "lightness" to lightness
        )
    }
}
