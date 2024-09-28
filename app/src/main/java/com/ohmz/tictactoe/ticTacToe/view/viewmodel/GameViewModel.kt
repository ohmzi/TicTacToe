package com.ohmz.tictactoe.ticTacToe.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ohmz.tictactoe.ticTacToe.model.UserAction
import com.ohmz.tictactoe.ticTacToe.model.BoardCellValue
import com.ohmz.tictactoe.ticTacToe.model.GameState
import com.ohmz.tictactoe.ticTacToe.model.VictoryType

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())

    val boardCellValue: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,

        )

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.TurnPlayed -> {
                addValueToBoard(action.cellNo)
            }

            UserAction.PlayAgainButtonClicked -> gameReset()
        }
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardCellValue.getValue(cellNo) != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardCellValue[cellNo] = BoardCellValue.CIRCLE
            if (checkForVictory(BoardCellValue.CIRCLE)) {
                state = state.copy(
                    hintText = "Player '0' Won",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (hasBoardFull()) {
                state = state.copy(
                    hintText = "Game Over",
                    drawCount = state.drawCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = false
                )
            } else {
                state = state.copy(
                    hintText = "Player 'X' turn", currentTurn = BoardCellValue.CROSS
                )
            }

        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardCellValue[cellNo] = BoardCellValue.CROSS
            if (checkForVictory(BoardCellValue.CROSS)) {
                state = state.copy(
                    hintText = "Player 'X' Won",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (hasBoardFull()) {
                state = state.copy(
                    hintText = "Game Over",
                    drawCount = state.drawCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = false
                )
            } else {
                state = state.copy(
                    hintText = "Player '0' turn", currentTurn = BoardCellValue.CIRCLE
                )
            }
        }
    }

    private fun checkForVictory(boardValue: BoardCellValue): Boolean {
        val winningCombinations = listOf(
            listOf(1, 2, 3) to VictoryType.HORIZONTAL1,
            listOf(4, 5, 6) to VictoryType.HORIZONTAL2,
            listOf(7, 8, 9) to VictoryType.HORIZONTAL3,
            listOf(1, 4, 7) to VictoryType.VERTICAL1,
            listOf(2, 5, 8) to VictoryType.VERTICAL2,
            listOf(3, 6, 9) to VictoryType.VERTICAL3,
            listOf(1, 5, 9) to VictoryType.DIAGONAL1,
            listOf(3, 5, 7) to VictoryType.DIAGONAL2
        )

        for ((combination, victoryType) in winningCombinations) {
            if (combination.all { boardCellValue[it] == boardValue }) {
                state = state.copy(victoryType = victoryType)
                return true
            }
        }

        return false
    }
    private fun hasBoardFull(): Boolean {
        if (boardCellValue.containsValue(BoardCellValue.NONE)) return false
        return true
    }

    private fun gameReset() {
        boardCellValue.forEach { (i, _) ->
            boardCellValue[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Player '0' turn",
            currentTurn = BoardCellValue.CIRCLE,
            victoryType = VictoryType.NONE,
            hasWon = false
        )
    }
}