package com.yusuf.components.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val rainbowColors = listOf(
    Color.Red,
    Color.Green,
    Color.Blue,
    Color.Magenta,
    Color.LightGray,
    Color.Red
)

fun Modifier.rgbBorder(
    strokeWidth : Dp,
    shape: Shape,
    colorList : List<Color> = rainbowColors,
    brush: (androidx.compose.ui.geometry.Size) -> Brush = { size ->
        Brush.sweepGradient(colorList)
    },
    duration: Int
)= composed { composed {
    val infiniteTransition = rememberInfiniteTransition(label = "rainbow")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween (
                durationMillis = duration, // Adjust the duration as needed
                easing = LinearEasing// Linear easing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepGradientAnimation"
    )

    Modifier.clip(shape)
        .drawWithCache{

            val strokeWidthPx = strokeWidth.toPx()
            val outline: Outline = shape.createOutline(size, layoutDirection, this)
            onDrawWithContent {
                drawContent()
                with(drawContext.canvas.nativeCanvas){
                    val checkPoint = saveLayer(null, null)
                    drawOutline(
                        outline = outline,
                        color = Color.Gray ,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = strokeWidthPx * 2,
                        )
                    )

                    rotate(angle){
                        drawCircle(
                            brush = brush(size),
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                    restoreToCount(checkPoint)
                }
            }
        }
}

}


@Preview
@Composable
fun RainbowBorderPreview() {
    Column {
        Box(
            modifier = Modifier
                .rgbBorder(
                    strokeWidth = 8.dp,
                    shape = RectangleShape,
                    duration = 5000,

                )
                .size(200.dp)
        )
        Box(
            modifier = Modifier
                .rgbBorder(strokeWidth = 8.dp, shape = CircleShape, duration = 5000)
                .size(200.dp)
        )


    }

}
