package com.yusuf.components.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.yusuf.components.ui.components.SearchBar
import com.yusuf.components.ui.util.ScaffoldWithAppBar


@Composable
fun SearchBarScreen(onBackClick: () -> Unit = {}){
    ScaffoldWithAppBar(title = "SearchBarScreen", onBackClick =onBackClick) {

        val focusManager = LocalFocusManager.current

        Column(modifier = Modifier.fillMaxSize().pointerInput(Unit){
            detectTapGestures {
                focusManager.clearFocus()
            }
        }) {
            val text = remember {
                mutableStateOf("")
            }


            Box(modifier = Modifier.pointerInput(Unit) {
                detectTapGestures {}
            }) {
                SearchBar(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearch = {},
                    onClear = {text.value = ""},
                )
            }
        }


    }


}
