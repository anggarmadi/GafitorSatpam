package com.example.gafitorsatpam.ui.fe.dataParkir

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dwarsh.pushnotificationsample.MyFirebaseMessagingService
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.DataComp.ReportData
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.parkirComp.CardUserParkir
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.service.MyFireBaseMessagingService
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkirDataScreen(navController: NavController, vm: GafitoViewModel) {
    val parkirs = vm.parkirs.value

    Scaffold(
        topBar = { TopBarMenu(screen = "Data Parkir")},
        bottomBar = { BottomBar(
            selectedItem = BottomBarItem.PARKIRDATA,
            navController = navController
        ) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                MyFirebaseMessagingService.sendMessage("Haloo",
                    "Laporan",
                    "fkaugfsdnfsrksugsfsi")
            }) {
                Icon(painter = painterResource(id = R.drawable.gafito), contentDescription ="Kirim Notif" )
            }
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
//                .padding(bottom = 32.dp)

        ) {
//        your code compose her
            ReportData(navController = navController, parkirs = parkirs)

        }
    }
}
