package processor

import java.math.BigDecimal

fun inputMainMenuChoice(): MainMenuChoice {
    print("Your choice: ")

    val choice = MainMenuChoice.valueOfInt(readln().toInt())
    println()

    return choice
}

fun inputTransposeMenuChoice(): TransposeMenuChoice {
    print("Your choice: ")

    return TransposeMenuChoice.valueOfInt(readln().toInt())
}

fun printMenu() {
    println(
        """
        1. Add matrices
        2. Multiply matrix by a constant
        3. Multiply matrices
        4. Transpose matrix
        5. Calculate a determinant
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

fun transposeRoutine() {
    printTransposeMenu()
    val choice = inputTransposeMenuChoice()
    val matrix = inputMatrix(InputState.ONLY)

    val transformFun: (Int, Int) -> Pair<Int, Int>
    val newN: Int
    val newM: Int
    when (choice) {
        TransposeMenuChoice.MAIN -> {
            transformFun = { x, y -> Pair(y, x) }
            newN = matrix.m
            newM = matrix.n
        }
        TransposeMenuChoice.SIDE -> {
            transformFun = { x, y -> Pair(matrix.n - y - 1, matrix.m - x - 1) }
            newN = matrix.m
            newM = matrix.n
        }
        TransposeMenuChoice.VERTICAL_LINE -> {
            transformFun = { x, y -> Pair(x, matrix.m - y - 1) }
            newN = matrix.n
            newM = matrix.m
        }
        TransposeMenuChoice.HORIZONTAL_LINE -> {
            transformFun = { x, y -> Pair(matrix.n - x - 1, y) }
            newN = matrix.n
            newM = matrix.m
        }
    }
    val resMatrix = matrix.transpose(transformFun, newN, newM)

    printResult(resMatrix)
}

fun determinantRoutine() {
    val matrix = inputMatrix(InputState.ONLY)

    printResult(matrix.determinant())
}

fun printTransposeMenu() {
    println(
        """
        1. Main diagonal
        2. Side diagonal
        3. Vertical line
        4. Horizontal line
    """.trimIndent()
    )
}

fun printResult(resMatrix: Matrix) {
    println("The result is:")
    println(resMatrix)
}

fun printResult(result: BigDecimal) {
    println("The result is:")
    println(result)
}
