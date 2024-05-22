package com.yusuf.components.ui.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithAppBar(
    title: String,
    showBackIcon: Boolean = true,
    onBackClick: () -> Unit = {},
    content: @Composable () -> Unit,

    ) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = title)
        },

            navigationIcon = {
                if (showBackIcon) {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                        )
                    }
                }

            })
    }, content = { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            content()
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewScaffoldWithBackAppBar() {

    MaterialTheme {
        ScaffoldWithAppBar(title = "Title", content = {
            Text("Content goes here")
        }, onBackClick = {})
    }
}