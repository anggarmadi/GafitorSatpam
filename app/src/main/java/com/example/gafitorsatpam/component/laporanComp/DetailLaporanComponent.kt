@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.gafitorsatpam.component.laporanComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.main.CommonImage
import com.example.gafitorsatpam.main.NavParam
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import com.example.gafitorsatpam.ui.theme.Warning
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLaporan(navController: NavController, vm: GafitoViewModel, laporan: LaporanData) {

    val userData = vm.userData.value


    Box(
        //            color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)

        ) {
            CommonImage(
                data = laporan.laporanImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .size(128.dp)
                    .clip(CircleShape)
            )
            TextField(
                value = laporan.nomorPolisi ?: "",
                label = { Text(text = "Nomor Polisi") },
                enabled = false,
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = laporan.merek ?: "",
                label = { Text(text = "Merek Kendaraan") },
                enabled = false,
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = laporan.warna ?: "",
                label = { Text(text = "Warna Kendaraan") },
                enabled = false,
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            TextField(
                value = "19/10/2023, 04:43 PM",
                label = { Text(text = "Tanggal Laporan") },
                enabled = false,
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()

            )
            TextField(
                value = "Laporan Kunci Tertinggal",
                label = { Text(text = "Deskripsi Laporan") },
                enabled = false,
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()

            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Button(
                    onClick = { navigateTo(navController, DestinationScreen.EditLaporan, NavParam("laporan", laporan) ) },
                    colors = ButtonDefaults.buttonColors(Warning),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Ubah")
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Hapus")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailLaporaPreview() {
    GafitorSatpamTheme {
    }
}