package com.photovault.editor.tools.exposure

class ExposureTool {
    var value: Float = 0f
    var highlights: Float = 0f
    var shadows: Float = 0f

    fun apply(value: Float) {
        this.value = value.coerceIn(-2f, 2f)
    }

    fun applyHighlights(value: Float) {
        this.highlights = value.coerceIn(-1f, 1f)
    }

    fun applyShadows(value: Float) {
        this.shadows = value.coerceIn(-1f, 1f)
    }

    fun reset() {
        value = 0f
        highlights = 0f
        shadows = 0f
    }

    fun export(): Map<String, Float> {
        return mapOf(
            "exposure" to value,
            "highlights" to highlights,
            "shadows" to shadows
        )
    }
}
