package com.photovault.image_engine.pipeline

import android.graphics.Bitmap
import com.photovault.image_engine.filters.ContrastFilter
import com.photovault.image_engine.filters.ExposureFilter
import com.photovault.image_engine.filters.SaturationFilter

class RenderPipeline {
    private var currentBitmap: Bitmap? = null
    private val exposureFilter = ExposureFilter()
    private val contrastFilter = ContrastFilter()
    private val saturationFilter = SaturationFilter()

    fun loadImage(bitmap: Bitmap) {
        currentBitmap = bitmap.copy(bitmap.config, true)
    }

    fun applyExposure(value: Float): Bitmap? {
        currentBitmap = currentBitmap?.let { exposureFilter.apply(it, value) }
        return currentBitmap
    }

    fun applyContrast(value: Float): Bitmap? {
        currentBitmap = currentBitmap?.let { contrastFilter.apply(it, value) }
        return currentBitmap
    }

    fun applySaturation(value: Float): Bitmap? {
        currentBitmap = currentBitmap?.let { saturationFilter.apply(it, value) }
        return currentBitmap
    }

    fun getCurrentBitmap(): Bitmap? = currentBitmap

    fun reset(originalBitmap: Bitmap) {
        currentBitmap = originalBitmap.copy(originalBitmap.config, true)
    }

    fun export(): Bitmap? = currentBitmap
}
