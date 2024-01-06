package com.example.gafitorsatpam.ui.fe.laporanSatpam

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarAtas
//import com.example.gafitorsatpam.component.laporanComp.LaporanList
import com.example.gafitorsatpam.component.laporanComp.ListLaporanView
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.main.NavParam
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListLaporanScreen(navController: NavController, vm: GafitoViewModel) {
    val userData = vm.userData.value
    val isLoading = vm.inProgress.value

    val laporansLoading = vm.refreshLaporanProgress.value
    val laporans = vm.laporans.value

    Scaffold(
        topBar = { TopBarAtas("List Laporan Satpam") },
        bottomBar = {
            BottomBar(
                selectedItem = BottomBarItem.LAPORPARKIR,
                navController = navController
            )
        },
        modifier = Modifier
//            .padding(16.dp)
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            ListLaporanView(
                isContextLoading = isLoading,
                laporans = laporans,
                laporansLoading = laporansLoading,
                modifier = Modifier
                    .weight(1f)
                    .padding(1.dp)
                    .fillMaxSize()
            ) { laporan ->
                navController.currentBackStackEntry?.savedStateHandle?.set("laporan", laporan)
                navigateTo(
                    navController,
                    DestinationScreen.DetailLaporan,
                    NavParam("laporan", laporan),
                )

                Log.d("laporan", "Laporan yang dikirim: $laporan")
                Log.d("laporan", "Argumen navigasi: ${navController.currentBackStackEntry?.arguments}")
            }
            //kalau make tampilan galeri
//            LaporanList(
//                isContextLoading = isLoading,
//                laporansLoading = laporansLoading,
//                laporans = laporans,
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(1.dp)
//                    .fillMaxSize(),
//            ) { laporan ->
//                navigateTo(
//                    navController = navController,
//                    DestinationScreen.DetailLaporan,
//                    NavParam("laporan", laporan)
//                )
//            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LaporanPreview() {
    GafitorSatpamTheme {
    }
}