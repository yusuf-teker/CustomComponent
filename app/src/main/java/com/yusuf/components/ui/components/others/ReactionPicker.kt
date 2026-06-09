package com.yusuf.components.ui.components.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Immutable
data class ReactionItem(
    val id: String,
    val content: @Composable () -> Unit
)
@Composable
fun ReactionPicker(
    reactions: List<ReactionItem>,
    onReactionSelected: (ReactionItem) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier.pointerInput(Unit) {

                detectTapGestures(

                    onLongPress = {
                        expanded = true
                    }
                )
            }
        ) {
            content()
        }

        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-72).dp),

            enter = fadeIn() +
                    scaleIn(
                        initialScale = 0.7f
                    ),

            exit = fadeOut() +
                    scaleOut()
        ) {

            Surface(
                shape = RoundedCornerShape(100),
                tonalElevation = 6.dp,
                shadowElevation = 8.dp
            ) {

                Row(
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    reactions.forEach { reaction ->

                        ReactionButton(

                            reaction = reaction,

                            onClick = {

                                onReactionSelected(
                                    reaction
                                )

                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun ReactionButton(
    reaction: ReactionItem,
    onClick: () -> Unit
) {

    var pressed by remember {
        mutableStateOf(false)
    }

    val scale by animateFloatAsState(
        targetValue =
            if (pressed) 1.35f else 1f,
        animationSpec = spring(
            dampingRatio =
                Spring.DampingRatioMediumBouncy
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .graphicsLayer {

                scaleX = scale
                scaleY = scale
            }
            .pointerInput(Unit) {

                detectTapGestures(

                    onPress = {

                        pressed = true

                        tryAwaitRelease()

                        pressed = false
                    },

                    onTap = {
                        onClick()
                    }
                )
            }
            .padding(4.dp)
    ) {

        reaction.content()
    }
}