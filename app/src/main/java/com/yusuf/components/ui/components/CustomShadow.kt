package com.yusuf.components.ui.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.scale
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomShadow(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(150.dp, 150.dp)
            .shadowWithClipIntersect(
                elevation = (2).dp,
                shape = CircleShape,
                ambientColor = Color.Red,
                spotColor = Color.Red
            )
            .background(color = Color.Green)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Button"
        )
    }
}


fun Modifier.shadowWithClipIntersect(
    elevation: Dp,
    shape: Shape = RectangleShape,
    clip: Boolean = elevation > 0.dp,
    ambientColor: Color = DefaultShadowColor,
    spotColor: Color = DefaultShadowColor,
): Modifier = this
    .drawWithCache {
        //  bottom shadow offset in Px based on elevation
        val bottomOffsetPx = elevation.toPx() * 2.2f
        // Adjust the size to extend the bottom by the bottom shadow offset
        val adjustedSize = Size(size.width, size.height + bottomOffsetPx)
        val outline = shape.createOutline(adjustedSize, layoutDirection, this)
        val path = Path().apply { addOutline(outline) }
        onDrawWithContent {
            clipPath(path, ClipOp.Intersect) {
                this@onDrawWithContent.drawContent()
            }
        }
    }
    .shadow(elevation, shape, clip, ambientColor, spotColor)


@Preview(showBackground = true)
@Composable
fun CustomShadowPrew(){
    Column (Modifier.fillMaxSize()){

        CustomShadow()


    }
}
