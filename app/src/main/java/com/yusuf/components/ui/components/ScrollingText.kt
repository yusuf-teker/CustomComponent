package com.yusuf.components.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollingText(
    text: String,
    delayMillis: Int = 1000,
    iteration: Int = 10,
    velocity: Dp = 50.dp,
    animationTimeOut: Long = 50000
) {
    val velocity = remember { mutableStateOf(velocity) }
    val iteration = remember { mutableIntStateOf(iteration) }
    Text(
        modifier = Modifier.basicMarquee(
                iterations = iteration.intValue,
                animationMode = MarqueeAnimationMode.Immediately,
                delayMillis = delayMillis,
                velocity = velocity.value,

                ), text = text
    )
    LaunchedEffect(Unit) {
        delay(animationTimeOut)
        velocity.value = 0.dp
        iteration.intValue = 0

    }

}

@Preview
@Composable
fun PreviewScrollingText() {
    MaterialTheme {
        Surface {
            ScrollingText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque convallis libero nec sapien vehicula, non feugiat ex faucibus. Nulla facilisi. Vivamus vehicula condimentum lacus, non pharetra orci. Integer vel justo nec libero luctus dapibus. Proin vel urna ligula. Nullam sit amet magna nec purus suscipit tempor."
            )
        }
    }
}