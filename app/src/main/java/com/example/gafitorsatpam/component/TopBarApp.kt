package com.example.gafitorsatpam.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@Composable
fun TopBarAtas(screen: String, navController: NavController) {
//    val navController = naturalOrder<>()
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.padding(end = 16.dp))
            Text(
                text = screen,
//                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    GafitorSatpamTheme {
        val navController = rememberNavController()
        TopBarAtas(screen = "Gafito", navController)
    }
}