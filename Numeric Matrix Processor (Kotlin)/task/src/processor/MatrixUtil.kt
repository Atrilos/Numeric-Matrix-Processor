package processor

import java.math.BigDecimal

fun inputChoice(): MenuChoice {
    print("Your choice: ")

    val choice = MenuChoice.valueOfInt(readln().toInt())
    println()

    return choice
}

fun printMenu() {
    println(
        """
        1. Add matrices
        2. Multiply matrix by a constant
        3. Multiply matrices
        0. Exit
    """.trimIndent()
    )
}


fun inputMatrix(state: InputState): Matrix {
    val matrixNum = state.value
    print("Enter size of ${matrixNum}matrix: ")
    val (n, m) = readln().split(" ").map { it.toInt() }
    println("Enter ${matrixNum}matrix:")
    val arr = MutableList(n) { MutableList(m) { BigDecimal.ZERO } }

    for (i in 0 until n) {
        val line = readln().split(" ").map { it.toBigDecimal() }
        require(line.size == m) { "Input has wrong size" }
        line.let {
            for (j in it.indices) {
                arr[i][j] = it[j]
            }
        }
    }

    return Matrix(n, m, arr)
}

fun addMatrixRoutine() {
    val matrixA = inputMatrix(InputState.FIRST)
    val matrixB = inputMatrix(InputState.SECOND)

    printResult(matrixA + matrixB)
}

fun multiplyConstRoutine() {
    val matrix = inputMatrix(InputState.ONLY)
    println("Enter constant: ")
    val constant = readln().toBigDecimal()

    printResult(matrix * constant)
}

fun multiplyMatricesRoutine() {
    val matrixA = inputMatrix(InputState.FIRST)
    val matrixB = inputMatrix(InputState.SECOND)

    printResult(matrixA * matrixB)
}

fun printResult(resMatrix: Matrix) {
    println("The result is:")
    println(resMatrix)
}