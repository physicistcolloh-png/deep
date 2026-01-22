package com.photovault.utils

import android.graphics.Bitmap

object ImageUtils {

    fun scaleBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        if (bitmap.width <= maxWidth && bitmap.height <= maxHeight) return bitmap

        val ratio = minOf(
            maxWidth.toFloat() / bitmap.width,
            maxHeight.toFloat() / bitmap.height
        )

        val newWidth = (bitmap.width * ratio).toInt()
        val newHeight = (bitmap.height * ratio).toInt()

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    fun rotateBitmap(bitmap: Bitmap, angle: Float): Bitmap {
        val matrix = android.graphics.Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun cropBitmap(bitmap: Bitmap, left: Int, top: Int, width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(bitmap, left, top, width, height)
    }

    fun getAspectRatio(bitmap: Bitmap): Float {
        return bitmap.width.toFloat() / bitmap.height
    }

    fun addWatermark(bitmap: Bitmap, watermarkText: String): Bitmap {
        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(result)
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = 50f
            alpha = 200
        }

        canvas.drawText(
            watermarkText,
            (bitmap.width - 100).toFloat(),
            (bitmap.height - 50).toFloat(),
            paint
        )

        return result
    }
}
