package com.ohmz.tictactoe.ticTacToe.model

sealed class UserAction {

    object PlayAgainButtonClicked : UserAction()

    data class TurnPlayed (val cellNo: Int) : UserAction()
}