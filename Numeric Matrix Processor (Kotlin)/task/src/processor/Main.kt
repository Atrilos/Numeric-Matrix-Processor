package processor

fun main() {
    do {
        printMenu()
        when (inputChoice()) {
            MenuChoice.ADD -> addMatrixRoutine()
            MenuChoice.MULTIPLY_CONST -> multiplyConstRoutine()
            MenuChoice.MULTIPLY_MATRICES -> multiplyMatricesRoutine()
            MenuChoice.EXIT -> break
        }
    } while (true)
}
