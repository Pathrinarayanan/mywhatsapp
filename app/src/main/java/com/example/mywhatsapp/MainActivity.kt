package com.example.mywhatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mywhatsapp.ui.theme.MywhatsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MywhatsappTheme {
                val navController = rememberNavController()
               Scaffold(
                   bottomBar = { CustomBottomNavigationBar(navController) }
               ) { paddingValues ->

                   NavHost(
                       navController= navController,
                       startDestination = BottomBarScreen.Status.route,
                       modifier = Modifier.padding(paddingValues)
                       ){
                       composable(BottomBarScreen.Status.route){
                           Text("Hello Status")
                       }
                       composable(BottomBarScreen.Call.route){
                           Text("Hello Call")
                       }
                       composable(BottomBarScreen.Camera.route){
                           Text("Hello Camera")

                       }
                       composable(BottomBarScreen.Chat.route){
                           Text("Hello Chat")
                       }
                       composable(BottomBarScreen.Settings.route){
                           Text("Hello Settings")
                       }
                   }
               }
            }
        }
    }
}

