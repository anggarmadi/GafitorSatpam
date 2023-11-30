package com.example.gafitorsatpam.ui.fe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.laporanComp.DetailLaporan
import com.example.gafitorsatpam.component.laporanComp.ListLaporanView
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailLapScreen() {
    Scaffold(
        topBar = {TopBarAtas("Detail Laporan")}
    ) {
        Column(
            modifier = Modifier.padding(top = 64.dp)
        ) {
            DetailLaporan()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailLaporanPrev() {
    GafitorSatpamTheme {
        DetailLapScreen()
    }
}