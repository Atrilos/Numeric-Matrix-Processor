package processor

fun main() {
    val matrixA = inputMatrix()
    val matrixB = inputMatrix()

    if (matrixA.n != matrixB.n || matrixA.m != matrixB.m) {
        println("ERROR")
        return
    }

    val resMatrix: Matrix = matrixA + matrixB

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

        val resArr = elementData.toMutableList().map { it.toMutableList() }

        for (i in elementData.indices) {
            for (j in elementData[i].indices) {
                resArr[i][j] += other.elementData[i][j]
            }
        }

        return Matrix(n, m, resArr)
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (i in elementData.indices) {
            sb.append(elementData[i].joinToString(" ")).append("\n")
        }

        return sb.toString()
    }
}
