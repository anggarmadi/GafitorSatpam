package com.example.gafitorsatpam.component.DataComp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@Composable
fun ParkirData(modifier: Modifier = Modifier){

    ReportData(
        countKendaraan = 70
    )

}

@Composable
fun ReportData(countKendaraan: Int, modifier: Modifier = Modifier){
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
                    text = "$countKendaraan",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Kendaraan",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.padding(32.dp))
                Button(onClick = { /*TODO*/ }) {
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
        ParkirData()
    }
}