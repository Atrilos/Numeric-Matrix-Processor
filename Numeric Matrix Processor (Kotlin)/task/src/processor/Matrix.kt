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

    fun transpose(transformFun: (Int, Int) -> Pair<Int, Int>, nn: Int, mm: Int): Matrix {
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
        require(n == m) { "Can't find determinant for non-square matrix"}

        fun determinantHelper(matrix: MutableList<MutableList<BigDecimal>>, size: Int): BigDecimal {
            var res: BigDecimal = BigDecimal.ZERO

            if (size == 1) return matrix[0][0]

            val temp = MutableList(size) {MutableList(size) {BigDecimal.ZERO} }
            var sign = BigDecimal.ONE

            fun getCofactor(q: Int) {
                var i = 0
                var j = 0

                for (row in 0 until size) {
                    for (col in 0 until size) {
                        if (row != 0 && col != q) {
                            temp[i][j++] = matrix[row][col]
                            if (j == size - 1) {
                                j = 0
                                i++
                            }
                        }
                    }
                }
            }

            for (i in 0 until size) {
                getCofactor(i)
                res += sign * matrix[0][i] * determinantHelper(temp, size - 1)
                sign = -sign
            }

            return res
        }

        return determinantHelper(elementDataToMutableList(), n)
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
}