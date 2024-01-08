package com.example.gafitorsatpam.ui.fe.laporanSatpam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.laporanComp.FormEditLaporan
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLaporanScreen(navController: NavController, vm: GafitoViewModel, laporan: LaporanData) {

    val laporanAja = laporan
    var nomorPolisi by rememberSaveable { mutableStateOf(laporanAja?.nomorPolisi ?: "") }
    var merek by rememberSaveable { mutableStateOf(laporanAja?.merek ?: "") }
    var warna by rememberSaveable { mutableStateOf(laporanAja?.warna ?: "") }
    var description by rememberSaveable { mutableStateOf(laporanAja?.description ?: "") }
    var laporanImage by rememberSaveable { mutableStateOf(laporanAja?.laporanImage ?: "") }

    Scaffold(
        topBar = { TopBarAtas("Edit Laporan", navController) }

    ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 8.dp)

        ) {
            FormEditLaporan(
                vm = vm,
                laporan = laporan,
                nomorPolisi = nomorPolisi,
                merek = merek,
                warna = warna,
                description = description,
                onNomorPolisiChange = { nomorPolisi = it },
                onMerekChange = { merek = it },
                onWarnaChange = { warna = it },
                onDescriptionChange = { description = it },
                onSave = {  },
                encodedUri = laporanImage
            )

        }
    }
}

@Preview
@Composable
fun EditLaporanPreview() {
    GafitorSatpamTheme {
    }
}