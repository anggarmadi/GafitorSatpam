package com.example.gafitorsatpam.component.DataComp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.data.ParkirData
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@Composable
fun ReportData(modifier: Modifier = Modifier, navController: NavController, parkirs: List<ParkirData>,){
    Surface() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
            ) {
                Text(
                    text = "Jumlah Kendaraan",
                    fontSize = 20.sp,
                    //            fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                )
                Text(
                    text = "${parkirs.size}",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Kendaraan",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = { navigateTo(navController, DestinationScreen.ListParkir) },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp),
//                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "Detail")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    GafitorSatpamTheme {
    }
}