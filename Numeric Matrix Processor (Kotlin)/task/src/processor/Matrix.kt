package processor

import java.math.BigDecimal
import java.math.RoundingMode

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

    fun transpose(nn: Int, mm: Int, transformFun: (Int, Int) -> Pair<Int, Int>): Matrix {
        val resArr = MutableList(nn) { MutableList(mm) { BigDecimal.ZERO } }

        for (i in resArr.indices) {
            for (j in resArr[i].indices) {
                val (ni, nj) = transformFun(i, j)
                resArr[i][j] = elementData[ni][nj]
            }
        }

        return Matrix(m, n, resArr)
    }

    fun determinant(): BigDecimal {
        require(n == m) { "Can't find determinant for non-square matrix" }

        if (n == 1) {
            return elementData[0][0]
        }

        var determinant = BigDecimal.ZERO
        for (col in 0 until m) {
            val sign = if (col % 2 == 0) BigDecimal.ONE else -BigDecimal.ONE
            determinant += sign * elementData[0][col] * getSubMatrix(0, col).determinant()
        }

        return determinant
    }

    private fun getSubMatrix(row: Int, col: Int): Matrix {
        val resArr = MutableList(n - 1) { MutableList(m - 1) { BigDecimal.ZERO } }

        for (i in 0 until n - 1) {
            for (j in 0 until m - 1) {
                resArr[i][j] = elementData[if (i >= row) i + 1 else i][if (j >= col) j + 1 else j]
            }
        }

        return Matrix(n - 1, m - 1, resArr)
    }

    private fun elementDataToMutableList(): MutableList<MutableList<BigDecimal>> {
        return elementData.toMutableList().map { it.toMutableList() } as MutableList
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (i in elementData.indices) {
            sb.append(elementData[i].joinToString(" ", postfix = "\n"))
        }

        return sb.toString()
    }

    fun inverse(): Matrix {
        val det = determinant()

        require(det != BigDecimal.ZERO) { "This matrix doesn't have an inverse." }

        val cofactorMatrix = MutableList(n) { MutableList(m) { BigDecimal.ZERO } }

        for (i in 0 until n) {
            for (j in 0 until m) {
                val sign = if ((i + j) % 2 == 0) BigDecimal.ONE else -BigDecimal.ONE
                val cofactorComponent = sign * getSubMatrix(i, j).determinant()
                cofactorMatrix[i][j] = (
                        (if (cofactorComponent.scale() < 2)
                            cofactorComponent.setScale(2, RoundingMode.HALF_UP)
                        else
                            cofactorComponent).divide(det, RoundingMode.HALF_UP))
                    .stripTrailingZeros()
            }
        }

        return Matrix(n, m, cofactorMatrix).transpose(m, n) { x, y -> Pair(y, x) }
    }
}