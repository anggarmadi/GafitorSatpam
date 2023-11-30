package com.example.gafitorsatpam.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.model.BottomBarItem
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme


@Composable
fun BottomBar(modifier: Modifier = Modifier, posisi: Int = 0) {
    NavigationBar(modifier) {
        val bottomNavigation = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.txt_data),
                icon = painterResource(id = R.drawable.data_parkir)
            ),
            BottomBarItem(
                title = stringResource(id = R.string.txt_home),
                icon = painterResource(id = R.drawable.scan_qr)
            ),
            BottomBarItem(
                title = stringResource(id = R.string.txt_laporan),
                icon = painterResource(id = R.drawable.report)
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = it.title == bottomNavigation[posisi].title,
                onClick = { },
                icon = { Icon(painter = it.icon, contentDescription = it.title) },
                label = { Text(text = it.title) })
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    GafitorSatpamTheme {
        BottomBar()
    }
}