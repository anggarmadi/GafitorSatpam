package com.example.gafitorsatpam.ui.fe.laporanSatpam

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.laporanComp.ListLaporanView
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LaporanScreen() {
    Scaffold(
        topBar = {TopBarAtas("List Laporan Satpam")},
        modifier = Modifier
//            .padding(16.dp)
    ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            ListLaporanView()

        }

    }
}

@Preview(showBackground = true)
@Composable
fun LaporanPreview() {
    GafitorSatpamTheme {
        LaporanScreen()
    }
}