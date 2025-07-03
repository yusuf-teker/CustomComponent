package com.yusuf.components.ui.navigation

import androidx.compose.runtime.Composable
import com.yusuf.components.ui.screens.AnimationsScreen
import com.yusuf.components.ui.screens.CustomButtonsScreen
import com.yusuf.components.ui.screens.CustomIconsScreen
import com.yusuf.components.ui.screens.DraggableListScreen
import com.yusuf.components.ui.screens.ResponsiveTextScreen
import com.yusuf.components.ui.screens.RgbBackgroundScreen
import com.yusuf.components.ui.screens.ScrollingTextScreen
import com.yusuf.components.ui.screens.SearchBarScreen
import com.yusuf.components.ui.screens.TabbedScreenScreen

object ScreenResponsiveTextDest : Destination {
    override val route = "responsiveText"
    override val label = "Responsive Text"
    @Composable
    override fun Content(onBack: () -> Unit) {
        ResponsiveTextScreen(onBack)
    }
}

object ScreenScrollingTextDest : Destination {
    override val route = "scrollingText"
    override val label = "Scrolling Text"
    @Composable
    override fun Content(onBack: () -> Unit) {
        ScrollingTextScreen("Sample long text ".repeat(20), onBack)
    }
}

object ScreenTabbedScreenDest : Destination {
    override val route = "tabbedScreen"
    override val label = "Tabbed Screen"
    @Composable
    override fun Content(onBack: () -> Unit) {
        TabbedScreenScreen(onBack)
    }
}

object ScreenSearchBarDest : Destination {
    override val route = "searchBar"
    override val label = "Search Bar"
    @Composable
    override fun Content(onBack: () -> Unit) {
        SearchBarScreen(onBack)
    }
}

object ScreenCustomButtonsDest : Destination {
    override val route = "customButtons"
    override val label = "Custom Buttons"
    @Composable
    override fun Content(onBack: () -> Unit) {
        CustomButtonsScreen(onBack)
    }
}

object ScreenAnimationsDest : Destination {
    override val route = "animations"
    override val label = "Animations"
    @Composable
    override fun Content(onBack: () -> Unit) {
        AnimationsScreen(onBack)
    }
}

object RgbBackgroundScreenDest : Destination {
    override val route = "rgbBackground"
    override val label = "Rgb Background"
    @Composable
    override fun Content(onBack: () -> Unit) {
        RgbBackgroundScreen(onBack)
    }
}

object DraggableListScreenDest : Destination {
    override val route = "draggableList"
    override val label = "Draggable List"
    @Composable
    override fun Content(onBack: () -> Unit) {
        DraggableListScreen(onBack)
    }
}

object ScreenCustomIconsDest : Destination {
    override val route = "customIcons"
    override val label = "Custom Icons"
    @Composable
    override fun Content(onBack: () -> Unit) {
        CustomIconsScreen(onBack)
    }
}

val allDestinations = listOf(
    ScreenResponsiveTextDest,
    ScreenScrollingTextDest,
    ScreenTabbedScreenDest,
    ScreenSearchBarDest,
    ScreenCustomButtonsDest,
    ScreenAnimationsDest,
    RgbBackgroundScreenDest,
    DraggableListScreenDest,
    ScreenCustomIconsDest
)
