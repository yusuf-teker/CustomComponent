package com.yusuf.components.ui.components

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


@Composable
fun DraggableLetters(){
    val letters = "Hello Compose".toCharArray()
    var enabled by remember { mutableStateOf(false) }
    var dragAmount by remember { mutableStateOf(Offset.Zero) }
    val delayTime = 200


    val backgroundColor by animateColorAsState(
        targetValue = if (enabled) Color.Blue else Color.Red,
        animationSpec = tween(durationMillis = delayTime)
    )
    Row(modifier =  Modifier
        .pointerInput(Unit) {

        detectDragGestures(
            onDragEnd = {
                dragAmount = Offset.Zero

                enabled = !enabled
            },


        ) { change, _ ->

            dragAmount = change.position
            Log.d("DraggableLetters", "Drag amount: $dragAmount")
            change.consume()
        }
    }) {
        letters.forEachIndexed { index, letter ->

            val animatedOffset by animateOffsetAsState(
                targetValue = dragAmount,
                animationSpec = tween(
                    easing = LinearEasing,
                    durationMillis = delayTime,
                    delayMillis = index * 20
                )
            )
            Log.d("DraggableLetters", "DelayMillis: $index * 50")
            Text(
                text = letter.toString(),
                fontSize = 32.sp,
                modifier = Modifier

                    .offset {

                        IntOffset(
                            animatedOffset.x.roundToInt(),
                            animatedOffset.y.roundToInt()
                        )
                    }
                    .background(backgroundColor)
                    .padding(2.dp)
            )
        }
    }


}


/*
@Composable
fun DraggableLetters() {
    val letters = "Hello Compose".toCharArray()
    var dragAmount by remember { mutableStateOf(Offset.Zero) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        dragAmount = Offset.Zero
                    }
                ) { change, _ ->
                    dragAmount = change.position
                    change.consumeAllChanges()
                }
            }
    ) {
        letters.forEachIndexed { index, letter ->
            val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

            // Continuous tracking of dragAmount for smooth animation
            LaunchedEffect(dragAmount) {
                scope.launch {
                    offset.animateTo(
                        targetValue = dragAmount,
                        animationSpec = tween(
                            easing = LinearEasing,
                            durationMillis = 200,  // Increase or decrease to adjust speed
                            delayMillis = index * 50
                        )
                    )
                }
            }

            Text(
                text = letter.toString(),
                fontSize = 32.sp,
                modifier = Modifier
                    .offset {
                        IntOffset(
                            offset.value.x.roundToInt(),
                            offset.value.y.roundToInt()
                        )
                    }
                    .background(if (index % 2 == 0) Color.Blue else Color.Red)
                    .padding(2.dp)
            )
        }
    }
}
*/
/*
@Composable
fun DraggableLetters() {
    val letters = "Hello Compose".toCharArray()
    var dragAmount by remember { mutableStateOf(Offset.Zero) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        dragAmount = Offset.Zero
                    }
                ) { change, _ ->
                    dragAmount = change.position
                    change.consumeAllChanges()
                }
            }
    ) {
        letters.forEachIndexed { index, letter ->
            val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

            // Continuous animation that doesn't stop when a new drag event occurs

            // Synchronized animation control
            LaunchedEffect(dragAmount) {
                scope.launch {
                    offset.animateTo(
                        targetValue = dragAmount,
                        animationSpec = tween(
                            durationMillis = 500,
                            delayMillis = index * 10,
                            easing = LinearEasing
                        )
                    )
                }
            }

            Text(
                text = letter.toString(),
                fontSize = 32.sp,
                modifier = Modifier
                    .offset {
                        IntOffset(
                            offset.value.x.roundToInt(),
                            offset.value.y.roundToInt()
                        )
                    }
                    .background(if (index % 2 == 0) Color.Blue else Color.Red)
                    .padding(2.dp)
            )
        }
    }
}

*/
/*
import kotlinx.coroutines.awaitAll
@Composable
fun DraggableLetters() {
    val letters = "Hello Compose".toCharArray()
    var dragAmount by remember { mutableStateOf(Offset.Zero) }
    val scope = rememberCoroutineScope()
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    // List of Animatable instances for each letter
    val animatableOffsets = remember {
        List(letters.size) { Animatable(Offset.Zero, Offset.VectorConverter) }
    }
    // Start all animations in parallel with delay for each
    // Start all animations in parallel with a staggered start
    LaunchedEffect(dragAmount) {
        letters.indices.forEach { index ->
            scope.launch {
                delay(Duration.ofMillis((index * 100).toLong())) // Stagger start of each animation
                animatableOffsets[index].animateTo(
                    targetValue = dragAmount,
                    animationSpec = tween(
                        durationMillis = 300, // Duration for animation
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    Row(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        dragAmount = Offset.Zero
                    }
                ) { change, _ ->
                        dragAmount = change.position
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                    change.consumeAllChanges()
                }
            }
    ) {
        letters.forEachIndexed { index, letter ->
            val animatedOffset = animatableOffsets[index]

            Text(
                text = letter.toString(),
                fontSize = 32.sp,
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    /*.offset {
                        IntOffset(
                            animatedOffset.value.x.roundToInt(),
                            animatedOffset.value.y.roundToInt()
                        )
                    }*/
                    .background(if (index % 2 == 0) Color.Blue else Color.Red)
                    .padding(5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DraggableLettersPreview(){
    DraggableLetters()
}*/
