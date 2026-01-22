package com.photovault.image_engine.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

class ExposureFilter {

    fun apply(bitmap: Bitmap, value: Float): Bitmap {
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

        return result
    }
}

class ContrastFilter {

    fun apply(bitmap: Bitmap, value: Float): Bitmap {
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

        return result
    }
}

class SaturationFilter {

    fun apply(bitmap: Bitmap, value: Float): Bitmap {
        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()

        val colorMatrix = ColorMatrix().apply {
            setSaturation(1 + value / 100f)
        }
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        return result
    }
}
