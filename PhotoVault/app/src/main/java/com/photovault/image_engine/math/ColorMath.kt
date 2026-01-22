package com.photovault.image_engine.math

object ColorMath {

    fun rgbToHsv(r: Int, g: Int, b: Int): FloatArray {
        val rf = r / 255f
        val gf = g / 255f
        val bf = b / 255f

        val cmax = maxOf(rf, gf, bf)
        val cmin = minOf(rf, gf, bf)
        val delta = cmax - cmin

        val h = when {
            delta == 0f -> 0f
            cmax == rf -> 60f * (((gf - bf) / delta) % 6f)
            cmax == gf -> 60f * (((bf - rf) / delta) + 2f)
            else -> 60f * (((rf - gf) / delta) + 4f)
        }

        val s = if (cmax == 0f) 0f else delta / cmax
        val v = cmax

        return floatArrayOf(h, s, v)
    }

    fun hsvToRgb(h: Float, s: Float, v: Float): IntArray {
        val c = v * s
        val hp = h / 60f
        val x = c * (1 - ((hp % 2) - 1).absoluteValue())

        val (r1, g1, b1) = when {
            hp < 1 -> Triple(c, x, 0f)
            hp < 2 -> Triple(x, c, 0f)
            hp < 3 -> Triple(0f, c, x)
            hp < 4 -> Triple(0f, x, c)
            hp < 5 -> Triple(x, 0f, c)
            else -> Triple(c, 0f, x)
        }

        val m = v - c
        val r = ((r1 + m) * 255).toInt()
        val g = ((g1 + m) * 255).toInt()
        val b = ((b1 + m) * 255).toInt()

        return intArrayOf(r, g, b)
    }
}

private fun Float.absoluteValue() = if (this < 0) -this else this
