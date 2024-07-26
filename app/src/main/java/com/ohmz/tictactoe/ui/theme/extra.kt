package com.ohmz.tictactoe.ui.theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class extra {

    @Preview(showBackground = true)
    @Composable
    fun TicTacToeGame() {
        var board by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
        var currentPlayer by remember { mutableStateOf("X") }
        var winner by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Tic Tac Toe",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Board(board, onCellClick = { row, col ->
                if (winner.isEmpty()) {
                    if (board[row][col].isEmpty()) {
                        board[row][col] = currentPlayer
                        if (checkWin(board, currentPlayer)) {
                            winner = currentPlayer
                        } else {
                            currentPlayer = if (currentPlayer == "X") "O" else "X"
                        }
                    }
                }
            })
            Spacer(modifier = Modifier.height(16.dp))
            if (winner.isNotEmpty()) {
                Text(
                    text = "Player $winner wins!",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }

    @Composable
    fun Board(board: Array<Array<String>>, onCellClick: (Int, Int) -> Unit) {
        Column {
            for (row in board.indices) {
                Row {
                    for (col in board[row].indices) {
                        Cell(
                            value = board[row][col],
                            onClick = { onCellClick(row, col) }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Cell(value: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = value,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    fun checkWin(board: Array<Array<String>>, player: String): Boolean {
        // Check rows
        for (row in board.indices) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true
            }
        }
        // Check columns
        for (col in board[0].indices) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) {
            return true
        }
        return false
    }

}