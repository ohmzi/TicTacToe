package com.ohmz.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ohmz.tictactoe.confetti.ConfettiViewModel
import com.ohmz.tictactoe.ui.theme.TicTacToeTheme
import com.ohmz.tictactoe.ticTacToe.view.TicTacToeGame
import com.ohmz.tictactoe.ticTacToe.view.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private val viewModelC by viewModels<ConfettiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
              val viewModel = viewModel<GameViewModel>()
               TicTacToeGame(viewModel = viewModel, viewModelC)
            }
        }
    }
}



