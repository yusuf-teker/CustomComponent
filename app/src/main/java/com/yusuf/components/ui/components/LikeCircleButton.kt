package com.yusuf.components.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LikeCircleWithBadge(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    brush: Brush =  Brush.linearGradient(
        colors = listOf(
            Color(0xFF8E59F6),
            Color(0xFF5D11F6)
        ),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    ),
    iconSize: Dp = 36.dp,
    iconColor: Color = Color.Black,
    badgeColor: Color = Color.Red,
    likeCount: Int = 5,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = shape,
                    clip = false,
                    ambientColor = Color.Blue,
                    spotColor = Color.Red
                )


                .background(
                    brush = brush,
                    shape = shape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Heart",
                tint = iconColor,
                modifier = Modifier.size(iconSize)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp, y = 2.dp)
                .size(35.dp)
                .offset(x = 5.dp, y = 5.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    clip = false
                )
                .background(
                    color = badgeColor,
                    shape = CircleShape
                )
        ) {
            Text(
                text = likeCount.toString(),
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun LikeCircleButtonPrew(){
    Column (Modifier.fillMaxSize()){
        LikeCircleWithBadge()

    }
}



