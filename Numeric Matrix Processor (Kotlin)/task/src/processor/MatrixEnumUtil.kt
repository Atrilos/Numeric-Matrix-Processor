package processor

enum class InputState(val value: String) {
    FIRST("first "), SECOND("second "), ONLY("")
}

enum class MenuChoice(val num: Int) {
    ADD(1),
    MULTIPLY_CONST(2),
    MULTIPLY_MATRICES(3),
    EXIT(0);

    companion object {
        fun valueOfInt(i: Int): MenuChoice {
            return values().find { it.num == i }
                ?: throw IllegalStateException("Value $i doesn't exist in allowed options")
        }
    }
}