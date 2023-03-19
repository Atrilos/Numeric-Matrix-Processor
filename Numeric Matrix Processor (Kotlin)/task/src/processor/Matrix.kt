package processor

import java.math.BigDecimal

data class Matrix(val n: Int, val m: Int, val elementData: List<List<BigDecimal>>) {

    operator fun plus(other: Matrix): Matrix {
        require(n == other.n && m == other.m) { "Can't addition matrices with different sizes" }

        val resArr = elementDataToMutableList()

        for (i in elementData.indices) {
            for (j in elementData[i].indices) {
                resArr[i][j] += other.elementData[i][j]
            }
        }

        return Matrix(n, m, resArr)
    }

    operator fun times(multiplyConst: BigDecimal): Matrix {
        val resArr = elementDataToMutableList()

        for (i in elementData.indices) {
            for (j in elementData[i].indices) {
                resArr[i][j] *= multiplyConst
            }
        }

        return Matrix(n, m, resArr)
    }

    operator fun times(other: Matrix): Matrix {
        require(m == other.n) { "Number of columns in first matrix must be equal to number of rows in the second" }

        val resArr = MutableList(n) { MutableList(other.m) { BigDecimal.ZERO } }

        for (i in 0 until n) {
            for (j in 0 until other.m) {
                for (k in 0 until m) {
                    resArr[i][j] += elementData[i][k] * other.elementData[k][j]
                }
            }
        }

        return Matrix(n, other.m, resArr)
    }

    private fun elementDataToMutableList(): List<MutableList<BigDecimal>> {
        return elementData.toMutableList().map { it.toMutableList() }
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (i in elementData.indices) {
            sb.append(elementData[i].joinToString(" ", postfix = "\n"))
        }

        return sb.toString()
    }
}