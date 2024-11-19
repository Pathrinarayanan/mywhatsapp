package com.example.mywhatsapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mywhatsapp.ui.theme.MywhatsappTheme
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import kotlin.coroutines.CoroutineContext
import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

class MainViewModelFactory(private val coroutineContext: CoroutineContext) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewmodel(coroutineContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class MainActivity : ComponentActivity() {
    var photoFile: File? = null
    val CAPTURE_IMAGE_REQUEST = 1
    var mCurrentPhotoPath: String? = null

    val viewModel: MainViewmodel by viewModels{
        MainViewModelFactory(Dispatchers.Main)
    }


    @RequiresApi(Build.VERSION_CODES.P)
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
                       startDestination = BottomBarScreen.Chat.route,
                       modifier = Modifier.padding(paddingValues)
                       ){
                       composable(BottomBarScreen.Status.route){
                           Text("Hello Status")
                       }
                       composable(BottomBarScreen.Call.route){
                           Text("Hello Call")
                       }
                       composable(BottomBarScreen.Camera.route){

                          AlbumScreen(modifier = Modifier,viewModel)
                       }
                       composable(BottomBarScreen.Chat.route){
                           window.statusBarColor = resources.getColor(R.color.status_bar_color)
                           ChatScreen()
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
data class AlbumViewState(

    val tempFileUrl: Uri? = null,

    val selectedPictures: List<ImageBitmap> = emptyList(),
)
sealed class Intent {
    data class OnPermissionGrantedWith(val compositionContext: Context): Intent()
    data object OnPermissionDenied: Intent()
    data class OnImageSavedWith (val compositionContext: Context): Intent()
    data object OnImageSavingCanceled: Intent()
    data class OnFinishPickingImagesWith(val compositionContext: Context, val imageUrls: List<Uri>): Intent()
}




