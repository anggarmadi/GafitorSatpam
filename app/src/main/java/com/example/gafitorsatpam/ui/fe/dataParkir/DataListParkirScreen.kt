package com.example.gafitorsatpam.ui.fe.dataParkir

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
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.component.TopBarAtas
import com.example.gafitorsatpam.component.parkirComp.ListUserParkir
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserParkirScreen(navController: NavController, vm: GafitoViewModel) {
    val isLoading = vm.inProgress.value
    val parkirs = vm.parkirs.value
    val parkirsLoading = vm.parkirProgress.value


    Scaffold(
        topBar = { TopBarAtas(screen = "List User Parkir", navController = navController)}
    ) {
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)

        ) {
//        your code compose here
            ListUserParkir(isContextLoading = isLoading, parkirsLoading = parkirsLoading, parkirs = parkirs)
        }
    }
}

@Preview
@Composable
fun UserParkirPreview() {
    GafitorSatpamTheme {
    }
}