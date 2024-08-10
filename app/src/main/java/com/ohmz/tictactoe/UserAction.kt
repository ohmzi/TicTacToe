package com.ohmz.tictactoe

sealed class UserAction {

    object PlayAgainButtonClicked : UserAction()

    data class TurnPlayed (val cellNo: Int) : UserAction()
}