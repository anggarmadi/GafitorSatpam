package com.example.gafitorsatpam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.main.NotificationMessage
import com.example.gafitorsatpam.ui.fe.DetailLapScreen
import com.example.gafitorsatpam.auth.LoginScreen
import com.example.gafitorsatpam.ui.fe.LaporKehilanganScreen
import com.example.gafitorsatpam.ui.fe.ParkirDataScreen
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
    object Login: DestinationScreen( "login")
    object DetailLaporan: DestinationScreen("detail_laporan")
    object ParkirData: DestinationScreen("parkir_data")
    object LaporParkir: DestinationScreen("lapor_parkir")
    object LaporKehilangan: DestinationScreen("lapor_kehilangan/{imageUri}") {
        fun createRoute(uri: String) = "lapor_kehilangan/$uri"
    }
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
        composable(DestinationScreen.DetailLaporan.route) {
            DetailLapScreen()
        }
        composable(DestinationScreen.ParkirData.route) {
            ParkirDataScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.LaporKehilangan.route) {
            LaporParkirScreen(navController = navController, vm =vm)
        }
        composable(DestinationScreen.LaporKehilangan.route) { navBackStackEntry ->
            val imageUri = navBackStackEntry.arguments?.getString("imageUri")
            imageUri?.let {
                LaporKehilanganScreen(navController = navController, vm = vm, encodedUri = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GafitoSAppPreview() {
    GafitorSatpamTheme {
    }
}