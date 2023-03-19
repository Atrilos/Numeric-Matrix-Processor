package processor

fun main() {
    do {
        printMenu()
        when (inputMainMenuChoice()) {
            MainMenuChoice.ADD -> addMatrixRoutine()
            MainMenuChoice.MULTIPLY_CONST -> multiplyConstRoutine()
            MainMenuChoice.MULTIPLY_MATRICES -> multiplyMatricesRoutine()
            MainMenuChoice.TRANSPOSE -> transposeRoutine()
            MainMenuChoice.EXIT -> break
        }
    } while (true)
}
