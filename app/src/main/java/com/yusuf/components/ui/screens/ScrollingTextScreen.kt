package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.ScrollingText
import com.yusuf.components.ui.util.ScaffoldWithAppBar

@Composable
fun ScrollingTextScreen(text: String, onBackClick: () -> Unit = {}){
   ScaffoldWithAppBar(
       title = "Scrolling Text",
       onBackClick = onBackClick
   ) {
       Box(Modifier.padding(8.dp)) {
           ScrollingText(text = text)
       }
   }

}