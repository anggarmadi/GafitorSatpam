@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.main.CommonProgressSpinner
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormLaporan(navController: NavController, vm: GafitoViewModel, encodedUri: String) {
    var licensePlateNumber by remember { mutableStateOf("") }
    var firstLetter by remember { mutableStateOf("") }
    var secondLetter by remember { mutableStateOf("") }
    var merek by remember { mutableStateOf("") }
    var warna by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val nomorPolisi = "$firstLetter $licensePlateNumber $secondLetter"
    var imageUri by remember { mutableStateOf(encodedUri) }

    val newLaporanImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri.toString()
        }

    val cameraImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { uri ->
        imageUri = uri.toString()
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        onValueChange = { merek = it },
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
                        onValueChange = { warna = it },
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
                        label = { Text(text = "Deskripsi") },
                        onValueChange = { description = it },
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
            onClick = {
                focusManager.clearFocus()
                //call the VM
                vm.onNewLaporan(Uri.parse(imageUri), nomorPolisi, merek, warna, description) {
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = "Kirim")
        }
        val isLoading = vm.inProgress.value
        if (isLoading) {
            CommonProgressSpinner()
        }

        Spacer(modifier = Modifier.padding(64.dp))
    }
}

