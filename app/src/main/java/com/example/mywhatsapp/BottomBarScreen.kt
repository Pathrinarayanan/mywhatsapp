package com.example.mywhatsapp

sealed class BottomBarScreen(
    val title :String,
    val route :String,
    val icon : Int
){
    object Status : BottomBarScreen("Status", "status", R.drawable.status_icon)
    object Call : BottomBarScreen("Call", "call", R.drawable.call_icon)
    object Camera : BottomBarScreen("Camera", "camera", R.drawable.camera_icon)
    object Chat : BottomBarScreen("Chats", "chat", R.drawable.chat_icon)
    object Settings : BottomBarScreen("Settings", "settings", R.drawable.settings_icon)
}

