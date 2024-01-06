package com.example.gafitorsatpam.main

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R

@Composable
fun NotificationMessage(vm: GafitoViewModel) {
    val notifState = vm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun CommonProgressSpinner() {
    Row(
        modifier = Modifier
            .alpha(0.5f)
            .background(Color.LightGray)
            .clickable(enabled = false) { }
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

    data class NavParam(
        val name: String,
        val value: Parcelable
    )


    fun navigateTo(navController: NavController, dest: DestinationScreen, vararg params: NavParam) {
        for (param in params) {
            navController.currentBackStackEntry?.arguments?.putParcelable(param.name, param.value)
        }
        navController.navigate(dest.route) {
            popUpTo(dest.route) {
                inclusive = false
            }
            launchSingleTop = true
        }
        Log.d("Navigation", "Navigated to ${dest.route} with params: $params")
    }

//fun navigateTo(navController: NavController, dest: DestinationScreen, vararg params: NavParam) {
//    val bundle = Bundle()
//    for (param in params) {
//        if (param.value is Parcelable) {
//            bundle.putParcelable(param.name, param.value as Parcelable)
//        }
//        // Add other types if needed
//    }
//
//    val navOptions = navOptions {
//        // Specify the start destination to clear the back stack
//        popUpTo(navController.graph.startDestinationId) {
//            inclusive = false // Do not include the start destination itself
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
//
//    navController.navigate(dest.route, bundle, navOptions)
//}


@Composable
fun CheckSignedIn(navController: NavController, vm: GafitoViewModel) {
    val alreadyLoggedIn = remember { mutableStateOf(false) }
    val signedIn = vm.signedIn.value
    if (signedIn && !alreadyLoggedIn.value) {
        alreadyLoggedIn.value = true
        navController.navigate(DestinationScreen.ParkirData.route) {
            popUpTo(0)
        }
    }
}

@Composable
fun CommonImage(
    data: String?,
    modifier: Modifier = Modifier
        .wrapContentSize()
        .clip(CircleShape)
        .size(100.dp),
    contentScale: ContentScale = ContentScale.Crop
) {
    val painter = rememberAsyncImagePainter(model = data)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
    if (painter.state is AsyncImagePainter.State.Loading) {
        CommonProgressSpinner()
    }
}

@Composable
fun UserImageCard(
    userImage: String?,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .size(100.dp),
) {
    Card(shape = CircleShape, modifier = modifier) {
        if (userImage.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        } else {
            CommonImage(data = userImage)
        }
    }
}

@Composable
fun LaporanImageCard(
    laporanImage: String?,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .size(80.dp)
) {
    Card(shape = CircleShape, modifier = modifier) {
        if (laporanImage.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.ic_image_placeholder),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        } else {
            CommonImage(data = laporanImage)
        }
    }
}