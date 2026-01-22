package com.photovault.editor.tools.curves

class CurvesTool {
    private val controlPoints = mutableListOf<Pair<Float, Float>>()

    fun addControlPoint(x: Float, y: Float) {
        controlPoints.add(Pair(x.coerceIn(0f, 1f), y.coerceIn(0f, 1f)))
        controlPoints.sortBy { it.first }
    }

    fun removeControlPoint(index: Int) {
        if (index in controlPoints.indices) {
            controlPoints.removeAt(index)
        }
    }

    fun moveControlPoint(index: Int, x: Float, y: Float) {
        if (index in controlPoints.indices) {
            controlPoints[index] = Pair(x.coerceIn(0f, 1f), y.coerceIn(0f, 1f))
            controlPoints.sortBy { it.first }
        }
    }

    fun getControlPoints(): List<Pair<Float, Float>> = controlPoints.toList()

    fun reset() {
        controlPoints.clear()
    }

    fun export(): List<Pair<Float, Float>> = controlPoints.toList()
}
