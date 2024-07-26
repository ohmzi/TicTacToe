package com.ohmz.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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


    fun onAction(action: UserActions) {
        when (action) {
            is UserActions.TurnPlayed -> {
                addValueToBoard(action.cellNo)
            }

            UserActions.PlayAgainButtonClicked -> TODO()
        }
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardCellValue.getValue(cellNo) != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardCellValue[cellNo] = BoardCellValue.CIRCLE
            state = state.copy(
                hintText = "Player 'X' turn", currentTurn = BoardCellValue.CROSS
            )
        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardCellValue[cellNo] = BoardCellValue.CROSS
            state = state.copy(
                hintText = "Player '0' turn", currentTurn = BoardCellValue.CIRCLE
            )
        }
    }
}