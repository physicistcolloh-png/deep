package com.photovault.editor.tools.retouch

class HealingTool {
    var brushSize: Float = 50f
    var hardness: Float = 50f
    var opacity: Float = 100f

    fun setBrushSize(size: Float) {
        this.brushSize = size.coerceIn(1f, 200f)
    }

    fun setHardness(hardness: Float) {
        this.hardness = hardness.coerceIn(0f, 100f)
    }

    fun setOpacity(opacity: Float) {
        this.opacity = opacity.coerceIn(0f, 100f)
    }

    fun export(): Map<String, Float> {
        return mapOf(
            "brushSize" to brushSize,
            "hardness" to hardness,
            "opacity" to opacity
        )
    }
}

class CloneTool {
    var brushSize: Float = 50f
    var opacity: Float = 100f
    var sourceX: Float = 0f
    var sourceY: Float = 0f

    fun setBrushSize(size: Float) {
        this.brushSize = size.coerceIn(1f, 200f)
    }

    fun setOpacity(opacity: Float) {
        this.opacity = opacity.coerceIn(0f, 100f)
    }

    fun setSource(x: Float, y: Float) {
        this.sourceX = x
        this.sourceY = y
    }

    fun export(): Map<String, Float> {
        return mapOf(
            "brushSize" to brushSize,
            "opacity" to opacity,
            "sourceX" to sourceX,
            "sourceY" to sourceY
        )
    }
}
