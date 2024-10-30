package com.yusuf.components.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoadingInitially: Boolean = false,
    loadingTimeOut: Long = 3000L,
    isRounded: Boolean = true,
    isShrinkOnLoading: Boolean = true,
    enabledColor: Color = MaterialTheme.colorScheme.primary,
    disabledColor: Color = MaterialTheme.colorScheme.onBackground,
    loadingColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    text: String
){
    val isLoading = remember { mutableStateOf(isLoadingInitially) }
    val isEnabled = remember { mutableStateOf(enabled) }
    val textWidth = remember { mutableStateOf(200.dp) }
    val isTextWidthCalculated = remember { mutableStateOf(false) }
    val buttonWidth by animateDpAsState(
        if (isLoading.value) textWidth.value else textWidth.value,
        tween(durationMillis = 500)
    )
    LaunchedEffect(key1 = isLoading.value){
        isEnabled.value = !isLoading.value
        delay(loadingTimeOut)
        isLoading.value = false
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {
                if (isEnabled.value){
                    isLoading.value = true
                    onClick()
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled.value) enabledColor else disabledColor,
                contentColor = textColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,

                ),
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(if (isRounded) 50.dp else 0.dp)
                )
                .width(buttonWidth)
                .align(Alignment.Center)
            ,
        ){
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Transparent),
                    color = loadingColor
                )
            }else{
                Text(
                    text = text,
                    modifier = Modifier.background(Color.Transparent),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        if (!isTextWidthCalculated.value) {
                            textWidth.value = textLayoutResult.size.width.dp
                            isTextWidthCalculated.value = true
                        }
                    }
                )
            }

        }
    }

}

