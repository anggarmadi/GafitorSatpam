package com.example.gafitorsatpam.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gafitorsatpam.GafitoViewModel
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@Composable
fun TopBarMenu(screen: String, vm: GafitoViewModel) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxWidth(),
//        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Log out",
                    Modifier.clickable { vm.onLogout() }
                )

            }
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = screen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun TopBarMenuPreview() {
    GafitorSatpamTheme {
//        TopBarMenu(screen = "Gafito", GafitoViewModel)
    }
}