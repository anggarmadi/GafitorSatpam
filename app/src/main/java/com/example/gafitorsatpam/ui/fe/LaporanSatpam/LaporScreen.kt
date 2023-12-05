package com.example.gafitorsatpam.ui.fe.LaporanSatpam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.laporanComp.FormLaporan
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLaporScreen() {
    Scaffold(
        topBar = { TopBarMenu(screen = "Laporan")}
    ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            FormLaporan()

        }

    }
}

@Preview
@Composable
fun AddLaporPreview() {
    GafitorSatpamTheme {
        AddLaporScreen()
    }
}