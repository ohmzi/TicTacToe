package com.ohmz.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ohmz.tictactoe.ui.theme.Aqua
import com.ohmz.tictactoe.ui.theme.Green

@Composable
fun Cross() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Green,
            strokeWidth = 25f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = Green,
            strokeWidth = 25f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width, y = 0f),
            end = Offset(x = 0f, y = size.height)
        )
    }
}

@Composable
fun Circle() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawCircle(
            color = Aqua,
            style = Stroke(width = 25f)
        )
    }

}

@Composable
@Preview(showBackground = true)
fun CrossPreview() {
    Column() {
        Cross()
        Circle()
    }
}