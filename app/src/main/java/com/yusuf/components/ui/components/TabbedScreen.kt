package com.yusuf.components.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TabbedScreen(
    initialPage: Int = 0,
    onTabChanged: (Int) -> Unit,
    tabBackgroundColor: Color = Color.Black,
    selectedTabColor: Color = Color.Green,
    unselectedTabColor: Color = Color.Gray,
    screens: List<Screen>
) {

    LaunchedEffect(true) {
        onTabChanged.invoke(initialPage)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val tabs = screens.map {
            TabRowItem(it.title)
        }
        val pagerState = rememberPagerState(
            initialPage = initialPage, initialPageOffsetFraction = 0f
        ) {
            screens.size
        }

        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            tabs = tabs,
            tabBackgroundColor = tabBackgroundColor,
            selectedTabColor = selectedTabColor,
            unselectedTabColor = unselectedTabColor,
            onTabChanged = { index, _ ->
                onTabChanged(index)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )


        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),

            ) { page ->
            screens[page].content()
        }
    }
}


@Preview
@Composable
fun TabbedScreenPreview() {

    val screenExample = Screen(
        title = "Example Tab Title",
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "EXAMPLE SCREEN CONTENT")
            }

        },
    )
    TabbedScreen(
        initialPage = 0,
        onTabChanged = {},
        screens = listOf(screenExample, screenExample),
        tabBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        selectedTabColor = MaterialTheme.colorScheme.primary,
        unselectedTabColor = MaterialTheme.colorScheme.secondary,
        )
}

data class Screen(
    val title: String,
    val content: @Composable () -> Unit,
)


@Composable
fun TabRow(
    selectedTabIndex: Int = 0,
    tabs: List<TabRowItem>,
    tabBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    selectedTabColor: Color = MaterialTheme.colorScheme.primary,
    unselectedTabColor: Color = MaterialTheme.colorScheme.secondary,
    onTabChanged: (Int, TabRowItem) -> Unit,
) {

    TabRow(selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        indicator = {},
        divider = {},
        modifier = Modifier
            .background(tabBackgroundColor)
            .padding(4.dp)

    ) {
        tabs.forEachIndexed { index, screen ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (selectedTabIndex == index) selectedTabColor else unselectedTabColor),

                ) {
                Tab(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    text = {
                        Text(
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = screen.title,
                            style = TextStyle.Default.copy(
                                color = if (selectedTabIndex == index) Color.Black else Color.DarkGray,
                            )
                        )
                    },
                    selected = selectedTabIndex == index,
                    onClick = {
                        onTabChanged.invoke(index, tabs[index])
                    },

                    )
            }


        }
    }
}

data class TabRowItem(
    val title: String
)

@Preview
@Composable
fun TabRowPreview() {
    val tabIndex = remember {
        mutableIntStateOf(0)
    }

    TabRow(tabIndex.intValue, tabs = listOf(
        TabRowItem("TAB TITLE 1"), TabRowItem("TAB TITLE 2")
    ), onTabChanged = { index, _ ->
        tabIndex.intValue = index
    })


}