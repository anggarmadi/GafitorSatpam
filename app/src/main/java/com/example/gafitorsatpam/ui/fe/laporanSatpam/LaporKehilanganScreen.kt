package com.example.gafitorsatpam.ui.fe.laporanSatpam

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.TopBarMenu
import com.example.gafitorsatpam.component.laporanComp.FormLaporan
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LaporKehilanganScreen(navController: NavController, vm: GafitoViewModel, encodedUri: String) {
//    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBarAtas(screen = "Lapor Kehilangan", navController) },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                navController.popBackStack("list_aporan", inclusive = true)
//            }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.add_icon),
//                    contentDescription = "Tambah Laporan"
//                )
//            }
//        },
//        bottomBar = {BottomBar(
//            selectedItem = BottomBarItem.REPORT,
//            navController = navController)}
    ) {
        Column(
            modifier = Modifier
                .padding(top = 64.dp)
        ) {
            FormLaporan(navController, vm, encodedUri)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LaporHilangPreview() {
    GafitorSatpamTheme {

    }
}