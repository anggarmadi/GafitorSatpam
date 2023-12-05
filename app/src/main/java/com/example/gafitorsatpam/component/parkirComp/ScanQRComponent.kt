package com.example.gafitorsatpam.component.parkirComp

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gafitorsatpam.R
import com.example.gafitorsatpam.ui.theme.GafitorSatpamTheme

@Composable
fun ViewScanQR() {
    Box(

    ) {
        Icon(painter = painterResource(id = R.drawable.scan_qr), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun ViewScanQRPreview() {
    GafitorSatpamTheme {
        ViewScanQR()
    }
}