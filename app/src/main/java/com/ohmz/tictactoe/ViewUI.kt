package com.ohmz.tictactoe

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ohmz.tictactoe.ui.theme.Blue
import com.ohmz.tictactoe.ui.theme.GrayBackground
import com.ohmz.tictactoe.ui.theme.LightGray
import com.ohmz.tictactoe.ui.theme.Red
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener
import nl.dionsegijn.konfetti.core.PartySystem
import nl.dionsegijn.xml.compose.ConfettiViewModel

@OptIn(ExperimentalUnsignedTypes::class)
@Composable
fun TicTacToeGame(viewModel: GameViewModel, viewModelC: ConfettiViewModel) {
    val viewModelC = viewModelC

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Player '0': ${state.playerCircleCount}", fontSize = 20.sp, color = Blue)
            Text(text = "Draw : ${state.drawCount}", fontSize = 20.sp, color = Blue)
            Text(text = "Player 'X': ${state.playerCrossCount}", fontSize = 20.sp, color = Blue)
        }

        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Blue,
            fontFamily = FontFamily.Serif,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            Grid()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f), columns = GridCells.Fixed(3)
            ) {
                viewModel.boardCellValue.forEach { (cellNo, boardCellValue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(
                                        UserAction.TurnPlayed(cellNo)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                visible = viewModel.boardCellValue[cellNo] != BoardCellValue.NONE,
                                enter = scaleIn(tween(durationMillis = 500))
                            ) {
                                when (boardCellValue) {
                                    BoardCellValue.CIRCLE -> {
                                        Circle()
                                    }

                                    BoardCellValue.CROSS -> Cross()
                                    else -> Unit
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon, enter = scaleIn(tween(durationMillis = 500))
                ) {
                    DrawVictoryLine(state = state)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon, enter = scaleIn(tween(durationMillis = 500))
                ) {
                    ConfettiUI(viewModelC)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = state.hintText, fontSize = 25.sp, modifier = Modifier.clickable {})
            Button(
                onClick = { viewModel.onAction(UserAction.PlayAgainButtonClicked) },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red, contentColor = Color.White
                )
            ) {
                Text(text = "Play Again", fontSize = 25.sp)
            }
        }
    }
}

@Composable
fun DrawVictoryLine(state: GameState) {
    when (state.victoryType) {
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()

        VictoryType.VERTICAL1 -> WinVerticalLine1()
        VictoryType.VERTICAL2 -> WinVerticalLine2()
        VictoryType.VERTICAL3 -> WinVerticalLine3()

        VictoryType.DIAGONAL1 -> WinDiagonalLine1()
        VictoryType.DIAGONAL2 -> WinDiagonalLine2()
        else -> Unit

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeGame(viewModel = GameViewModel(), viewModelC = ConfettiViewModel())
}


@Composable
fun ConfettiUI(viewModel: ConfettiViewModel = ConfettiViewModel()) {
    val state: ConfettiViewModel.State by viewModel.state.observeAsState(
        ConfettiViewModel.State.Idle,
    )
    val drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.ic_heart)
    when (val newState = state) {
        ConfettiViewModel.State.Idle -> {
            Column(
                modifier =
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                viewModel.explode()

            }
        }
        is ConfettiViewModel.State.Started ->
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = newState.party,
                updateListener =
                object : OnParticleSystemUpdateListener {
                    override fun onParticleSystemEnded(
                        system: PartySystem,
                        activeSystems: Int,
                    ) {
                        if (activeSystems == 0) viewModel.ended()
                    }
                },
            )
    }
}
