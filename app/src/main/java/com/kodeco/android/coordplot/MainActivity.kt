package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PlotSurface()
            }
        }
    }
}

@Composable
fun PlotSurface() {
    var xPercentage: Float by remember { mutableFloatStateOf(0.5f) }
    var yPercentage: Float by remember { mutableFloatStateOf(0.5f) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Map(xPercentage, yPercentage)
            Slider(
                value = xPercentage * 100f ,
                onValueChange = { newValue -> xPercentage = newValue / 100f  },
                valueRange = 0f..100f,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Slider(
                value = yPercentage * 100f,
                onValueChange = { newValue -> yPercentage = newValue / 100f },
                valueRange = 0f..100f,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PlotSurfacePreview() {
    MyApplicationTheme {
        PlotSurface()
    }
}

@Composable
fun Map(xPercent: Float, yPercent: Float, modifier: Modifier = Modifier) {
    val boxSize = 300
    val ballSize = 10
    Box(
        modifier = modifier
            .size(boxSize.dp)
            .background(Color.Blue)
            .padding((xPercent * (boxSize - ballSize)).dp, (yPercent * (boxSize - ballSize)).dp, 0.dp, 0.dp)
    ) {
        Box(
            modifier = modifier
                .size(ballSize.dp)
                .clip(shape = CircleShape)
                .background(Color.Green)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    MyApplicationTheme {
        Map(xPercent = 0.5f, yPercent = 0.5f)
    }
}
