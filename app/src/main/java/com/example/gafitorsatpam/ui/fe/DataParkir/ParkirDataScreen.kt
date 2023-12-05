package com.example.gafitorsatpam.ui.fe.DataParkir

import android.annotation.SuppressLint
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
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.DataComp.ReportData
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalDataScreen() {
    Scaffold(
        topBar = { TopBarMenu(screen = "Data Parkir")},
        bottomBar = { BottomBar(posisi = 0) }
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
        TotalDataScreen()
    }
}