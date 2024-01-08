@file:Suppress("DEPRECATION")

package com.example.gafitorsatpam

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.main.NotificationMessage
import com.example.gafitorsatpam.auth.LoginScreen
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.ui.fe.laporanSatpam.LaporKehilanganScreen
import com.example.gafitorsatpam.ui.fe.dataParkir.ParkirDataScreen
import com.example.gafitorsatpam.ui.fe.dataParkir.UserParkirScreen
import com.example.gafitorsatpam.ui.fe.laporanSatpam.DetailLaporanScreen
import com.example.gafitorsatpam.ui.fe.laporanSatpam.EditLaporanScreen
import com.example.gafitorsatpam.ui.fe.laporanSatpam.ListLaporanScreen
import com.example.gafitorsatpam.ui.fe.parkir.LaporParkirScreen
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GafitorSatpamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GafitoApp()
                }
            }
        }
    }
}


sealed class DestinationScreen(val route: String) {
    object Login : DestinationScreen("login")
    object DetailLaporan : DestinationScreen("detaillaporan")
    object ParkirData : DestinationScreen("parkir_data")
    object LaporParkir : DestinationScreen("lapor_parkir")
    object LaporKehilangan : DestinationScreen("lapor_kehilangan/{imageUri}") {
//        fun createRoute(uri: String) = "lapor_kehilangan/$uri"
    }
    object ListLaporan : DestinationScreen("list_laporan")
    object ListParkir : DestinationScreen("list_parkir")
    object EditLaporan : DestinationScreen("edit_laporan")
}


@Composable
fun GafitoApp() {
    val vm = hiltViewModel<GafitoViewModel>()
    val navController = rememberNavController()

    NotificationMessage(vm = vm)

    NavHost(navController = navController, startDestination = DestinationScreen.Login.route) {
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.ParkirData.route) {
            ParkirDataScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.ListParkir.route) {
            UserParkirScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.LaporParkir.route) {
            LaporParkirScreen(navController = navController, vm =vm)
        }
        composable(DestinationScreen.LaporKehilangan.route) { navBackStackEntry ->
            val imageUri = navBackStackEntry.arguments?.getString("imageUri")
            imageUri?.let {
                LaporKehilanganScreen(navController = navController, vm = vm, encodedUri = it)
            }
        }
        composable(DestinationScreen.ListLaporan.route) {
            ListLaporanScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.DetailLaporan.route) {
//            val laporanData = navController.currentBackStackEntry?.arguments?.getParcelable<LaporanData>("laporan")
            val laporanData = navController.previousBackStackEntry?.savedStateHandle?.get<LaporanData>("laporan")
            Log.d("laporan", "Argumen navigasi: ${navController.currentBackStackEntry?.arguments}")
            // Periksa apakah laporan adalah null
            if (laporanData == null) {
                // Tampilkan pesan kesalahan
                Log.e("laporan", "laporan tidak ditemukan nih, kosong")
            }
            val test = navController.currentBackStackEntry
            Log.d("laporanData", "laporanData to $laporanData")
            Log.d("Tst", "ada ga $test")

            laporanData?.let {
                DetailLaporanScreen(
                    navController = navController,
                    vm = vm,
                    laporan = laporanData
                )
            } ?: run {
                Text(text = "Laporan tidak ditemukan")
            }
            // Print for debugging
            println("laporanData: $laporanData")
            println("laporanData: $test")
        }
//        composable("detail_laporan/{laporanId}") { backStackEntry ->
//            val laporanId = backStackEntry.arguments?.getString("laporanId")
//            // Tampilkan DetailLaporanView dengan laporanId
//        }
        composable(DestinationScreen.EditLaporan.route) {
            val laporanData = navController.previousBackStackEntry?.savedStateHandle?.get<LaporanData>("laporan")
            Log.d("laporanDet", "Argumen navigasi: ${navController.currentBackStackEntry?.arguments}")
            // Periksa apakah laporan adalah null
            if (laporanData == null) {
                // Tampilkan pesan kesalahan
                Log.e("laporanD", "laporan tidak ditemukan nih, kosong")
            }
            val test = navController.currentBackStackEntry
            Log.d("DetData", "laporanData to $laporanData")
            Log.d("Tst2", "ada ga $test")

            laporanData?.let {
                EditLaporanScreen(
                    navController = navController,
                    vm = vm,
                    laporan = laporanData
                )
            } ?: run {
                Text(text = "Laporan tidak ditemukan")
            }
            // Print for debugging
            println("laporanData: $laporanData")
            println("laporanData: $test")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GafitoSAppPreview() {
    GafitorSatpamTheme {
    }
}