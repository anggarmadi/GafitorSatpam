package com.example.gafitorsatpam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.TopListParkir
import com.example.gafitorsatpam.component.laporanComp.FormLaporan
import com.example.gafitorsatpam.component.laporanComp.LaporComponentPreview
import com.example.gafitorsatpam.component.laporanComp.ListLaporanView
import com.example.gafitorsatpam.component.parkirComp.CardUserParkir
import com.example.gafitorsatpam.component.parkirComp.FormParkir
import com.example.gafitorsatpam.component.parkirComp.ListUserParkir
import com.example.gafitorsatpam.ui.fe.LaporanScreen
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

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
                    LaporanScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GafitorSatpamTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GafitoSApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopBarMenu(screen = "Home") },
        bottomBar = { BottomBar(posisi = 0) }) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)

        ) {
//        your code compose here
            CardUserParkir()
            CardUserParkir()
            CardUserParkir()
            CardUserParkir()
            CardUserParkir()
            CardUserParkir()
            CardUserParkir()

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GafitoSAppPreview() {
    GafitorSatpamTheme {
        GafitoSApp()
    }
}