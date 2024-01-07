package com.example.gafitorsatpam.ui.fe.parkir

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.parkirComp.FormParkir
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import com.example.gafitorsatpam.viewModel.BarrcodeScanner
import com.example.simpleqrscanner.ViewModel.QRScanner
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporParkirScreen(navController: NavController, vm: GafitoViewModel, qrScanner: QRScanner, hasil: MutableState<String>) {
    var barcodeScanner: BarrcodeScanner
    val hasil = hasil
    val context = LocalContext.current
    val view = LocalView.current
    val qrScanner = qrScanner
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    scope.launch {
                        barcodeScanner.startScan()
                        Log.d("status", "Bisa bisa")
                    }
                }else{
                    qrScanner.tampilkanKamera()
                }


            }) {
                Icon(
                    painter = painterResource(id = R.drawable.scan_qr),
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
            FormParkir(barcodeResult.value)
//            FormParkir(barcodeResult.value)


        }
    }
}
//@Composable
//fun RequestPermission() {
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted: Boolean ->
//            if (isGranted) {
//                // Permission granted
//            } else {
//                // Permission denied
//            }
//        }
//    )
//}

@Preview(showBackground = true)
@Composable
fun ParkirPreview() {
    GafitorSatpamTheme {
    }
}