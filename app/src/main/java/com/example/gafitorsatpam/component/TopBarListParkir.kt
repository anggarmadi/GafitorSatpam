package com.example.gafitorsatpam.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gafitorsatpam.model.IconList
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme



@Composable
fun TopListParkir() {
    Row {
        IconList(icon = Icons.Default.ArrowBack)
        Text(
            text = "List Data Parkir",
            modifier = Modifier
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopListParkirPreview() {
    GafitorSatpamTheme {
        TopListParkir()
    }
}