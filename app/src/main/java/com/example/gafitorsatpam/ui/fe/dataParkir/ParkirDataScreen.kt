package com.example.gafitorsatpam.ui.fe.dataParkir

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.DataComp.ReportData
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.parkirComp.CardUserParkir
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkirDataScreen(navController: NavController, vm: GafitoViewModel) {
    Scaffold(
        topBar = { TopBarMenu(screen = "Data Parkir")},
        bottomBar = { BottomBar(
            selectedItem = BottomBarItem.PARKIRDATA,
            navController = navController
        ) }
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
            ReportData(countKendaraan = 77)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TotalDataPreview() {
    GafitorSatpamTheme {
    }
}