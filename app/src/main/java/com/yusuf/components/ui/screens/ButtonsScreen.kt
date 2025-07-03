package com.yusuf.components.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.LikeCircleWithBadge
import com.yusuf.components.ui.components.LoadingButton
import com.yusuf.components.ui.util.ScaffoldWithAppBar


@Composable
fun CustomButtonsScreen(onBackClick: () -> Unit = {}) {

    ScaffoldWithAppBar(title = "Custom Buttons", onBackClick = onBackClick) {
        Column {
            Box(
                Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadingButton(
                    modifier = Modifier.padding(8.dp).width(200.dp),
                    text = "Loading Button",
                    onClick = {
                        // do something
                        Log.d("LoadingButton", "LoadingButton: clicked")
                    }
                )
            }

            LikeCircleWithBadge()

        }


    }

}
@Preview
@Composable
fun CustomButtonsScreenPreview() {
    CustomButtonsScreen()

}