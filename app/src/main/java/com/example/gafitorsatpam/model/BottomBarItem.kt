package com.example.gafitorsatpam.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.R

enum class BottomBarItem(val icon: Int, val navDestination: DestinationScreen, val text: String) {
    PARKIRDATA(R.drawable.data_parkir, DestinationScreen.ParkirData, "Data Parkir"),
    LAPORPARKIR(R.drawable.scan_qr, DestinationScreen.LaporKehilangan, "Scan QR"),
    REPORT(R.drawable.report, DestinationScreen.ListLaporan, "Report"),
}