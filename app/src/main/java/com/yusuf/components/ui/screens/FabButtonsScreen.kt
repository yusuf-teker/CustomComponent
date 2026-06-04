package com.yusuf.components.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.components.menufab.MenuFabItem
import com.yusuf.components.ui.components.menufab.MenuFloatingActionButton
import com.yusuf.components.ui.components.menufab.MenuFloatingActionButtonWithScale
import com.yusuf.components.ui.util.ScaffoldWithAppBar


@Composable
fun FabButtonsScreen(onBackClick: () -> Unit = {}) {

    ScaffoldWithAppBar(title = "Custom Buttons", onBackClick = onBackClick) {
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom){
            val menuItems = remember {
                mutableStateListOf(
                    MenuFabItem(
                        icon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                        label = "Mesaj Gönder"
                    ),
                    MenuFabItem(
                        icon = { Icon(Icons.Default.Phone, contentDescription = "Ara") },
                        label = "Ara"
                    ),
                    MenuFabItem(
                        icon = { Icon(Icons.Default.Share, contentDescription = "Paylaş") },
                        label = "Paylaş"
                    )
                )
            }

            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                MenuFloatingActionButtonWithScale(
                    srcIcon = Icons.Default.Add,
                    items = menuItems,
                    onFabItemClicked = { /* tıklandığında yapılacaklar */ }
                )
                MenuFloatingActionButton(
                    srcIcon = Icons.Default.Add,
                    items = menuItems,
                    onFabItemClicked = { /* tıklandığında yapılacaklar */ }
                )
            }



        }


    }

}
@Preview
@Composable
fun FabButtonsScreenPreview() {
    FabButtonsScreen()

}