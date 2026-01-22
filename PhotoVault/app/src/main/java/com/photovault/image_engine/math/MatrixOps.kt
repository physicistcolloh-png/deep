package com.photovault.image_engine.math

object MatrixOps {

    fun multiplyMatrices(
        a: Array<FloatArray>,
        b: Array<FloatArray>
    ): Array<FloatArray> {
        val rows = a.size
        val cols = b[0].size
        val result = Array(rows) { FloatArray(cols) }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                var sum = 0f
                for (k in a[0].indices) {
                    sum += a[i][k] * b[k][j]
                }
                result[i][j] = sum
            }
        }

        return result
    }

    fun inverseMatrix(matrix: Array<FloatArray>): Array<FloatArray>? {
        val n = matrix.size
        if (n != matrix[0].size) return null

        val augmented = Array(n) { i -> 
            FloatArray(2 * n) { j ->
                when {
                    j < n -> matrix[i][j]
                    j - n == i -> 1f
                    else -> 0f
                }
            }
        }

        // Gaussian elimination
        for (i in 0 until n) {
            // Find pivot
            var maxRow = i
            for (k in i + 1 until n) {
                if (augmented[k][i].absoluteValue() > augmented[maxRow][i].absoluteValue()) {
                    maxRow = k
                }
            }

            val temp = augmented[i]
            augmented[i] = augmented[maxRow]
            augmented[maxRow] = temp

            // Scale pivot row
            val pivot = augmented[i][i]
            if (pivot.absoluteValue() < 1e-10) return null

            for (j in 0 until 2 * n) {
                augmented[i][j] /= pivot
            }

            // Eliminate column
            for (k in 0 until n) {
                if (k != i) {
                    val factor = augmented[k][i]
                    for (j in 0 until 2 * n) {
                        augmented[k][j] -= factor * augmented[i][j]
                    }
                }
            }
        }

        // Extract inverse from right side
        return Array(n) { i ->
            FloatArray(n) { j ->
                augmented[i][n + j]
            }
        }
    }
}

private fun Float.absoluteValue() = if (this < 0) -this else this
