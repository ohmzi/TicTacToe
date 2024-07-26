package com.ohmz.tictactoe

sealed class UserActions {
    abstract val cellNo: Int

    object PlayAgainButtonClicked : UserActions() {
        override val cellNo: Int
            get() = TODO("Not yet implemented")
    }

    object TurnPlayed : UserActions() {
        override val cellNo: Int
            get() = TODO("Not yet implemented")
    }
}