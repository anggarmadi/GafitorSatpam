@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.gafitorsatpam.component.laporanComp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEditLaporan(
    vm: GafitoViewModel,
    laporan: LaporanData,
    nomorPolisi: String,
    merek: String,
    warna: String,
    description: String,
    onNomorPolisiChange: (String) -> Unit,
    onMerekChange: (String) -> Unit,
    onWarnaChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSave: () -> Unit
) {
    var licensePlateNumber by remember { mutableStateOf("") }
    var firstLetter by remember { mutableStateOf("") }
    var secondLetter by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        Column {
            Text(
                text = "Nomor Polisi",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {

                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = firstLetter,
                        onValueChange = {
                            firstLetter = it
                        },
                        label = { Text("HP") },
                        //                textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = licensePlateNumber,
                        onValueChange = {
                            licensePlateNumber = it
                        },
                        label = { Text("Nomor Polisi") },
                        //                textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.weight(2f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = secondLetter,
                        onValueChange = {
                            secondLetter = it
                        },
                        label = { Text("HK") },
                        //                textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Column {
            Text(
                text = "Informasi Kendaraan",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
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
                    modifier = Modifier
                        .padding(16.dp)

                ) {
                    TextField(
                        value = merek,
                        label = { Text(text = "Merek Kendaraan") },
                        onValueChange = onMerekChange,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                    )
                    TextField(
                        value = warna,
                        label = { Text(text = "Warna Kendaraan") },
                        onValueChange = onWarnaChange,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                    )
                    TextField(
                        value = description,
                        label = { Text(text = "Deskripsi Laporan") },
                        onValueChange = onDescriptionChange,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()

                    )
                }
            }
        }
        Button(
            onClick = { onSave.invoke() },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = "Simpan")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FormEditLaporanPreview() {
    GafitorSatpamTheme {
    }
}