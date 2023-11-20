package ru.nelshin.drawapp.ui.theme

import android.graphics.Paint.Cap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.drawscope.Stroke
import ru.nelshin.drawapp.ui.theme.PathData

@Composable

fun BottomPanel(onClick: (Color) -> Unit, onLineWidthChange: (Float) -> Unit, onChangeStyle: (StrokeCap) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList{color ->
            onClick(color)
        }
        CustomSlider{lineWidth ->
            onLineWidthChange(lineWidth)
        }
        LineShape{cap, ->
            onChangeStyle(cap)
        }
    }
}

@Composable

fun ColorList(onClick: (Color) -> Unit) {
    val colors = listOf(
        Color.Black,
        Color.White,
        Color.Yellow,
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Gray,
        Color.Magenta
    )
    LazyRow(modifier = Modifier.padding(10.dp)) {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onClick(color)
                    }
                    .size(40.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable

fun CustomSlider(onChage: (Float) -> Unit){
    var position by remember {
        mutableStateOf(0.05f)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(":ine width: ${(position * 100).toInt()}")
        Slider(value = position,
            onValueChange = {
                val tempPos = if(it > 0) it else 0.01f
                position = tempPos
                onChage(tempPos * 100)
            }
        )
    }

}

@Composable

fun LineShape(onChangeStyle: (StrokeCap) -> Unit) {
    val caps = listOf(
        StrokeCap.Butt,
        StrokeCap.Round,
        StrokeCap.Square
    )
    LazyRow(modifier = Modifier.padding(10.dp)){
        items(caps) {StrokeCap ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onChangeStyle(StrokeCap)
                    }
                    .size(30.dp)
                    .background(
                        Color.Gray,
                        (if (StrokeCap == androidx.compose.ui.graphics.StrokeCap.Round) CircleShape else RectangleShape)
                    )
            )
        }
    }
}