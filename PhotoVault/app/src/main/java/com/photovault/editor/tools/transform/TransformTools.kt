package com.photovault.editor.tools.transform

class CropTool {
    var left: Float = 0f
    var top: Float = 0f
    var right: Float = 1f
    var bottom: Float = 1f

    fun setCropRect(left: Float, top: Float, right: Float, bottom: Float) {
        this.left = left.coerceIn(0f, 1f)
        this.top = top.coerceIn(0f, 1f)
        this.right = right.coerceIn(0f, 1f)
        this.bottom = bottom.coerceIn(0f, 1f)
    }

    fun reset() {
        left = 0f
        top = 0f
        right = 1f
        bottom = 1f
    }

    fun export(): Map<String, Float> {
        return mapOf(
            "left" to left,
            "top" to top,
            "right" to right,
            "bottom" to bottom
        )
    }
}

class PerspectiveTool {
    var topLeft: Pair<Float, Float> = Pair(0f, 0f)
    var topRight: Pair<Float, Float> = Pair(1f, 0f)
    var bottomLeft: Pair<Float, Float> = Pair(0f, 1f)
    var bottomRight: Pair<Float, Float> = Pair(1f, 1f)

    fun reset() {
        topLeft = Pair(0f, 0f)
        topRight = Pair(1f, 0f)
        bottomLeft = Pair(0f, 1f)
        bottomRight = Pair(1f, 1f)
    }

    fun export(): Map<String, Pair<Float, Float>> {
        return mapOf(
            "topLeft" to topLeft,
            "topRight" to topRight,
            "bottomLeft" to bottomLeft,
            "bottomRight" to bottomRight
        )
    }
}

class RotateTool {
    var angle: Float = 0f

    fun rotate(degrees: Float) {
        this.angle = (angle + degrees) % 360f
    }

    fun setAngle(degrees: Float) {
        this.angle = degrees % 360f
    }

    fun reset() {
        angle = 0f
    }

    fun export(): Map<String, Float> {
        return mapOf("angle" to angle)
    }
}
