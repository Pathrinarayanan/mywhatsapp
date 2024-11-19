package com.example.mywhatsapp


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView


@Composable
fun CustomBottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Status,
        BottomBarScreen.Call,
        BottomBarScreen.Camera,
        BottomBarScreen.Chat,
        BottomBarScreen.Settings
    )

    // Get the current backstack entry to determine the selected screen
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .padding(vertical =12 .dp),

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        screens.forEach { screen ->
            val isSelected = currentDestination?.route == screen.route
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    tint = if (isSelected) Color.Blue else Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            if (!isSelected) {
                                navController.navigate(screen.route) {
                                    // Configure navigation behavior
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        }
                )
                Text(screen.title, fontSize = 10.sp, color = Color(0xFF545458))
            }
        }
    }
}
