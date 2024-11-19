package com.example.mywhatsapp


import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
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


@Composable
fun ChatScreenItem(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(R.drawable.oval),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 11.dp)
                .padding(start = 16.dp,end=12.dp)
                .size(52.dp)

            )
        Column(
            modifier = Modifier
                .weight(0.8f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Row {
                Text(
                    text = "Bathri Narayanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.1f)

                )
                Text(
                    text = "11/15/19",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF8E8E93),
                    modifier = Modifier

                )
            }
            Text(
                text = "Great things take time",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF8E8E93),
                modifier = Modifier

            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color(0x3c3c43aa))
                )
        }

        Image(
            painter = painterResource(R.drawable.right_arrow),
            contentDescription = null,
            modifier = Modifier.size(width = 7.dp, height = 12.dp).weight(0.1f)
        )
    }
}

@Composable
fun ChatTopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFF6F6F6))
            .padding(start = 16.dp, bottom = 12.dp, top = 12.dp, end =16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Edit", color = Color(0xFF007aff), fontSize = 17.sp)
        Text("Chats", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        Image(painter = painterResource(R.drawable.edit_icon), contentDescription = null,
            modifier = Modifier.size(24.dp))
    }
}


@Composable
fun ChatScreen(){
    val scollstate = rememberScrollState()
    Scaffold (
        topBar = {
            ChatTopBar()
        },

    ){
        padding->
        Column(modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(scollstate)) {
            for(i in 1..20){
                ChatScreenItem()
            }
        }
    }

}
@Composable
@RequiresApi(Build.VERSION_CODES.P)
fun AlbumScreen(modifier: Modifier = Modifier, viewModel: MainViewmodel) {

    val currentContext = LocalContext.current
    val viewState: AlbumViewState by viewModel.viewStateFlow.collectAsState()

    val pickImageFromAlbumLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { urls ->
        viewModel.onReceive(Intent.OnFinishPickingImagesWith(currentContext, urls))
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isImageSaved ->
        if (isImageSaved) {
            viewModel.onReceive(Intent.OnImageSavedWith(currentContext))
        } else {
            // handle image saving error or cancellation
            viewModel.onReceive(Intent.OnImageSavingCanceled)
        }
    }

    // launches camera permissions
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { permissionGranted ->
        if (permissionGranted) {
            viewModel.onReceive(Intent.OnPermissionGrantedWith(currentContext))
        } else {
            // handle permission denied such as:
            viewModel.onReceive(Intent.OnPermissionDenied)
        }
    }

    LaunchedEffect(key1 = viewState.tempFileUrl) {
        viewState.tempFileUrl?.let {
            cameraLauncher.launch(it)
        }
    }

    val context = LocalContext.current
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            // You can now open the camera or perform any camera-related action
        } else {
            Toast.makeText(context, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    val hasCameraPermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    Column(modifier = Modifier) {
        Row {
            Button(onClick = {
                // get user's permission first to use camera
               permissionLauncher.launch(android.Manifest.permission.CAMERA)

            }) {
                Text(text = "Take a photo")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                // Image picker does not require special permissions and can be activated right away
                val mediaRequest =
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                pickImageFromAlbumLauncher.launch(mediaRequest)
            }) {
                Text(text = "Pick a picture")
            }
        }


    }
    }