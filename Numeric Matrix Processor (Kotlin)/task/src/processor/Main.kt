package processor

fun main() {
    val matrixA = inputMatrix()
    val multiplyConst = readln().toInt()

    val resMatrix: Matrix = matrixA * multiplyConst

    println(resMatrix)
}


fun inputMatrix(): Matrix {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = MutableList(n) { MutableList(m) { 0 } }

    for (i in 0 until n) {
        val line = readln().split(" ").map { it.toInt() }
        require(line.size == m) { "Input has wrong size" }
        line.let {
            for (j in it.indices) {
                arr[i][j] = it[j]
            }
        }
    }

    return Matrix(n, m, arr)
}

data class Matrix(val n: Int, val m: Int, val elementData: List<List<Int>>) {

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

    operator fun times(multiplyConst: Int): Matrix {
        val resArr = elementDataToMutableList()

        for (i in elementData.indices) {
            for (j in elementData[i].indices) {
                resArr[i][j] *= multiplyConst
            }
        }

        return Matrix(n, m, resArr)
    }

    private fun elementDataToMutableList(): List<MutableList<Int>> {
        return elementData.toMutableList().map { it.toMutableList() }
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (i in elementData.indices) {
            sb.append(elementData[i].joinToString(" ")).append("\n")
        }

        return sb.toString()
    }
}
