package com.ohmz.tictactoe

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ohmz.tictactoe.ui.theme.Red

@Composable
fun Grid() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = Color.Black,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = canvasWidth / 3, y = 0f),
            end = Offset(x = canvasWidth / 3, y = canvasHeight)
        )
        drawLine(
            color = Color.Black,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = canvasWidth * 2 / 3, y = 0f),
            end = Offset(x = canvasWidth * 2 / 3, y = canvasHeight)
        )
        drawLine(
            color = Color.Black,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = canvasHeight / 3),
            end = Offset(x = canvasWidth, y = canvasHeight / 3)
        )
        drawLine(
            color = Color.Black,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = canvasHeight * 2 / 3),
            end = Offset(x = canvasWidth, y = canvasHeight * 2 / 3)
        )

    }
}

@Composable
fun WinHorizontalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 6),
            end = Offset(x = size.width, y = size.height / 6)
        )
    }
}

@Composable
fun WinHorizontalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 5 / 6),
            end = Offset(x = size.width, y = size.height * 5 / 6)
        )
    }
}

@Composable
fun WinHorizontalLine3() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 2),
            end = Offset(x = size.width, y = size.height / 2)
        )
    }
}

@Composable
fun WinVerticalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 6, y = 0f),
            end = Offset(x = size.width / 6, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLine3() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 5 / 6, y = 0f),
            end = Offset(x = size.width * 5 / 6, y = size.height)
        )
    }
}
@Composable
fun WinDiagonalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f / 6, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
    }
}
@Composable
fun WinDiagonalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width , y = 0f),
            end = Offset(x = 0f, y = size.height)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Grid()
    WinHorizontalLine1()
    WinHorizontalLine2()
    WinHorizontalLine3()
    WinVerticalLine1()
    WinVerticalLine2()
    WinVerticalLine3()
    WinDiagonalLine1()
    WinDiagonalLine2()
}