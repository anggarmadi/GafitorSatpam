@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gafitorsatpam.component.parkirComp

import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme
import com.example.gafitorsatpam.viewModel.BarrcodeScanner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormParkir(
    navController: NavController,
    vm: GafitoViewModel,
    barcodeValue: String?
) {
    var hasil = barcodeValue
    var licensePlateNumber by remember { mutableStateOf("") }
    var firstLetter by remember { mutableStateOf("") }
    var secondLetter by remember { mutableStateOf("") }

    val noPolisi = "$firstLetter $licensePlateNumber $secondLetter"

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo_png) ,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
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
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Characters,
                            imeAction = ImeAction.Next
                        ),
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
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
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
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Characters,
                            imeAction = ImeAction.Done
                        ),
                    )
                }
            }
        }
        Button(
            onClick = { vm.onCreateParkir(noPolisi) },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = hasil ?:"Belum Lagi")
        }
//        Button(
//            onClick = {  },
//            modifier = Modifier
//                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(8.dp),
//        ) {
//            Text(text = "$barcodeValue")
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormParkirPreview() {
    GafitorSatpamTheme {
//        FormParkir()
    }
}