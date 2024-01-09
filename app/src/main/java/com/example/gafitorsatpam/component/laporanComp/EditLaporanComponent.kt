@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.gafitorsatpam.component.laporanComp

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEditLaporan(
    navController: NavController,
    vm: GafitoViewModel,
    laporan: LaporanData,
    nomorPolisi: String,
    merek: String,
    warna: String,
    description: String,
//    uri: Uri,
    onNomorPolisiChange: (String) -> Unit,
    onMerekChange: (String) -> Unit,
    onWarnaChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    encodedUri: String
) {
    var licensePlateNumber by remember { mutableStateOf(nomorPolisi.split(" ")[1]) }
    var firstLetter by remember { mutableStateOf(nomorPolisi.split(" ")[0]) }
    var secondLetter by remember { mutableStateOf(nomorPolisi.split(" ")[2]) }

    var imageUri by rememberSaveable { mutableStateOf(encodedUri) }
    Log.d("encd","Disini ada ga urinya $encodedUri")

    val newLaporanImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri.toString()
        }

    var onNomorPolisiChange = "$firstLetter $licensePlateNumber $secondLetter"
    onNomorPolisiChange(onNomorPolisiChange)
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            Card {
                if (imageUri == "{imageUri}") {
                    Image(
                        painter = painterResource(id = R.drawable.ic_image_placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clickable { newLaporanImageLauncher.launch("image/*") },
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .fillMaxSize()
                            .clickable { newLaporanImageLauncher.launch("image/*") },
                        contentScale = ContentScale.Crop
                    )
                }
            }
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
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = licensePlateNumber,
                        onValueChange = {
                            licensePlateNumber = it
                        },
                        label = { Text("Nomor Polisi") },
                        //                textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.weight(2f),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = secondLetter,
                        onValueChange = {
                            secondLetter = it
                        },
                        label = { Text("HK") },
                        //                textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
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
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    TextField(
                        value = warna,
                        label = { Text(text = "Warna Kendaraan") },
                        onValueChange = onWarnaChange,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    TextField(
                        value = description,
                        label = { Text(text = "Deskripsi Laporan") },
                        onValueChange = onDescriptionChange,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )

                    )
                }
            }
        }
        Button(
            onClick = { vm.onUpdateLaporan(
                laporan.laporanId ?: "",
                Uri.parse(imageUri),
                nomorPolisi,
                merek,
                warna,
                description
            ) {
                navigateTo(navController, DestinationScreen.ListLaporan)
            }
                Log.d("encd","Disini ada ga urinya $encodedUri") },
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