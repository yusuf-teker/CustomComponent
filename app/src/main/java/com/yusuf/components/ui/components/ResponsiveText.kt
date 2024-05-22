package com.yusuf.components.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Center,
    textStyle: TextStyle = TextStyle.Default,
    onTextSizeChanged: (TextUnit) -> Unit,
    maxLines: Int = 1,
) {

    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = textStyle.fontSize,
        fontFamily = textStyle.fontFamily,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        lineHeight = textStyle.lineHeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                onTextSizeChanged(textStyle.fontSize.times(TEXT_SCALE_REDUCTION_INTERVAL))
            }
        },
    )
}

@Preview
@Composable
fun ResponsiveTextPreview() {
    Box(
        Modifier
            .width(120.dp)
            .height(40.dp)
    ) {
        ResponsiveText(
            text = "Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text Long Text",
            color = MaterialTheme.colorScheme.onBackground,
            textStyle = TextStyle.Default,
            onTextSizeChanged = {},

            )
    }

}
