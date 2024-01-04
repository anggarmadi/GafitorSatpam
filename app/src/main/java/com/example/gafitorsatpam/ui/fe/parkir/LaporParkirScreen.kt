package com.example.gafitorsatpam.ui.fe.parkir

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.component.BottomBar
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.parkirComp.FormParkir
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporParkirScreen(navController: NavController, vm: GafitoViewModel) {
    Scaffold(
        topBar = { TopBarAtas(screen = "Input Parkir") },
        bottomBar = { BottomBar(
            selectedItem = BottomBarItem.LAPORPARKIR,
            navController = navController
        )}
            ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            FormParkir()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkirPreview() {
    GafitorSatpamTheme {
    }
}