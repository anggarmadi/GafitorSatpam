package com.example.gafitorsatpam.ui.fe.parkir

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.parkirComp.FormParkir
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import com.example.gafitorsatpam.viewModel.BarrcodeScanner
import com.example.simpleqrscanner.ViewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporParkirScreen(navController: NavController, vm: GafitoViewModel) {
    var barcodeScanner: BarrcodeScanner
    val context = LocalContext.current
    val view = LocalView.current
    barcodeScanner = BarrcodeScanner(context)

    val barcodeResult = barcodeScanner.barcodeResult.collectAsState()

    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopBarMenu(screen = "Input Parkir") },
        bottomBar = { BottomBar(
            selectedItem = BottomBarItem.LAPORPARKIR,
            navController = navController
        )},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    barcodeScanner.startScan()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Scan QR"
                )
            }
        }
    ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            FormParkir(navController, vm)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkirPreview() {
    GafitorSatpamTheme {
    }
}