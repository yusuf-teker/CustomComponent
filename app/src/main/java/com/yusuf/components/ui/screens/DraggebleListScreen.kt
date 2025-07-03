package com.yusuf.components.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yusuf.components.ui.util.ScaffoldWithAppBar
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable


@Composable
fun DraggableListScreen(onBackClick: () -> Unit = {}) {

    ScaffoldWithAppBar(title = "Draggable Screen", onBackClick = onBackClick) {
        Box(
            Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            VerticalReorderList()
        }
    }

}

@Composable
fun VerticalReorderList() {
    val context = LocalContext.current
    val data = remember { mutableStateOf(List(100) { "Item $it" }) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    },
        onDragEnd = { startIndex, endIndex ->
           Toast.makeText(context, "startIndex = $startIndex, endIndex = $endIndex", Toast.LENGTH_SHORT).show()
        }
    )
    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .reorderable(state)
            .detectReorderAfterLongPress(state)
    ) {
        items(data.value, { it }) { item ->
            ReorderableItem(state, key = item) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                Column(
                    modifier = Modifier
                        .shadow(elevation.value)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(14.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}