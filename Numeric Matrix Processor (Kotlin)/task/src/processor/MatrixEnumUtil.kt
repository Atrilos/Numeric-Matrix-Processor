package processor

enum class InputState(val value: String) {
    FIRST("first "), SECOND("second "), ONLY("")
}

enum class MainMenuChoice(val num: Int) {
    ADD(1),
    MULTIPLY_CONST(2),
    MULTIPLY_MATRICES(3),
    TRANSPOSE(4),
    DETERMINANT(5),
    EXIT(0);

    companion object {
        fun valueOfInt(i: Int): MainMenuChoice {
            return values().find { it.num == i }
                ?: throw IllegalStateException("Value $i doesn't exist in allowed options")
        }
    }
}

enum class TransposeMenuChoice(val num: Int) {
    MAIN(1),
    SIDE(2),
    VERTICAL_LINE(3),
    HORIZONTAL_LINE(4);

    companion object {
        fun valueOfInt(i: Int): TransposeMenuChoice {
            return values().find { it.num == i }
                ?: throw IllegalStateException("Value $i doesn't exist in allowed options")
        }
    }
}
