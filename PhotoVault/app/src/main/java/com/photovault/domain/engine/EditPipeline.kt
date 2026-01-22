package com.photovault.domain.engine

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.photovault.data.model.EditStackModel
import com.photovault.data.model.EditStep

class EditPipeline {

    private var currentBitmap: Bitmap? = null
    private val editStack = mutableListOf<EditStep>()

    fun loadImage(bitmap: Bitmap) {
        currentBitmap = bitmap.copy(bitmap.config, true)
    }

    fun applyExposure(value: Float): Bitmap? {
        return currentBitmap?.let { bitmap ->
            val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)
            val paint = Paint()

            val colorMatrix = ColorMatrix().apply {
                setScale(
                    1 + value / 100f,
                    1 + value / 100f,
                    1 + value / 100f,
                    1f
                )
            }
            paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
            canvas.drawBitmap(bitmap, 0f, 0f, paint)

            currentBitmap = result
            editStack.add(EditStep("exposure_${System.currentTimeMillis()}", "Exposure", "exposure", mapOf("value" to value)))
            result
        }
    }

    fun applySaturation(value: Float): Bitmap? {
        return currentBitmap?.let { bitmap ->
            val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)
            val paint = Paint()

            val colorMatrix = ColorMatrix().apply {
                setSaturation(1 + value / 100f)
            }
            paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
            canvas.drawBitmap(bitmap, 0f, 0f, paint)

            currentBitmap = result
            editStack.add(EditStep("saturation_${System.currentTimeMillis()}", "Saturation", "saturation", mapOf("value" to value)))
            result
        }
    }

    fun applyContrast(value: Float): Bitmap? {
        return currentBitmap?.let { bitmap ->
            val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)
            val paint = Paint()

            val colorMatrix = ColorMatrix().apply {
                val scale = 1 + value / 100f
                val translate = (1 - scale) / 2
                set(
                    floatArrayOf(
                        scale, 0f, 0f, 0f, translate * 255,
                        0f, scale, 0f, 0f, translate * 255,
                        0f, 0f, scale, 0f, translate * 255,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            }
            paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
            canvas.drawBitmap(bitmap, 0f, 0f, paint)

            currentBitmap = result
            editStack.add(EditStep("contrast_${System.currentTimeMillis()}", "Contrast", "contrast", mapOf("value" to value)))
            result
        }
    }

    fun getCurrentBitmap(): Bitmap? = currentBitmap

    fun getEditStack(): List<EditStep> = editStack.toList()

    fun undoLastEdit(): Bitmap? {
        if (editStack.isNotEmpty()) {
            editStack.removeAt(editStack.size - 1)
            // In a real implementation, would rebuild from original
        }
        return currentBitmap
    }

    fun resetToOriginal(originalBitmap: Bitmap) {
        currentBitmap = originalBitmap.copy(originalBitmap.config, true)
        editStack.clear()
    }

    fun flattenImage(): Bitmap? = currentBitmap
}
