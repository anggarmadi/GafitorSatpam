package com.example.gafitorsatpam.component.laporanComp

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gafitorsatpam.DestinationScreen
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.main.CommonImage
import com.example.gafitorsatpam.main.CommonProgressSpinner
import com.example.gafitorsatpam.main.LaporanImageCard
import com.example.gafitorsatpam.main.NavParam
import com.example.gafitorsatpam.main.navigateTo
import com.example.gafitorsatpam.ui.theme.Warning
import java.text.SimpleDateFormat


@Composable
fun PhotoLaporan(imageUrl: String?) {
    Box{
        LaporanImageCard(
            laporanImage = imageUrl,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp),
        )
    }
}

@Composable
fun ListLaporanView(
    modifier: Modifier,
    isContextLoading: Boolean,
    laporansLoading: Boolean,
    laporans: List<LaporanData>,
    onLaporanClick: (LaporanData) -> Unit
) {
    if (laporansLoading) {
        CommonProgressSpinner()
    } else if (laporans.isEmpty()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isContextLoading) Text(text = "Tidak ada laporan tersedia")
        }
    } else {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
            items(items = laporans) { laporan ->
                CardLaporanView(laporan = laporan, onLaporanClick = onLaporanClick)
            }
        }
    }
}

//data class LaporanRow(

@Composable
fun CardLaporanView(
    laporan: LaporanData,
    modifier: Modifier = Modifier,
    onLaporanClick: (LaporanData) -> Unit
) {

    val laporanImageDisplay = laporan?.laporanImage ?: ""
    val nomorPolisiDisplay = laporan?.nomorPolisi ?: ""
    val merekDisplay = laporan?.merek ?: ""
    val warnaDisplay = laporan?.warna ?: ""
    val tanggalLaporan = laporan?.time ?: ""

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    val tanggal = simpleDateFormat.format(tanggalLaporan)

    val expanded = rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 4.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val timePadding by animateDpAsState(
        if (expanded.value) extraPadding + 40.dp else 0.dp
    )
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            .clickable {
                expanded.value = !expanded.value
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            PhotoLaporan(imageUrl = laporanImageDisplay)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(
                    text = nomorPolisiDisplay,
                    modifier = modifier,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = merekDisplay,
                    modifier = modifier,
                    color = Color.White
                )
                if (expanded.value) {
                    Text(
                        text = "$warnaDisplay",
                        color = Color.White,
                    )
                    Text(
                        text = "$tanggal",
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Button(
                        onClick = { laporan?.let { laporan -> onLaporanClick(laporan)
                            Log.d("laporan", "laporan nya adalah $laporan")}},
                        colors = ButtonDefaults.buttonColors(Warning),
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Details")
                    }
                }
            }
        }
        if (!expanded.value) {
            Text(
                text = "$tanggal",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
//                .padding(bottom = timePadding.coerceAtLeast(0.dp))
            )
        }
    }
}

//tampilan galeri
//    var laporan1: LaporanData? = null,
//    var laporan2: LaporanData? = null,
//    var laporan3: LaporanData? = null,
//) {
//    fun isfull() = laporan1 != null && laporan2 != null && laporan3 != null
//    fun add(laporan: LaporanData) {
//        if (laporan1 == null) {
//            laporan1 = laporan
//        } else if (laporan2 == null) {
//            laporan2 = laporan
//        } else if (laporan3 == null) {
//            laporan3 = laporan
//        }
//    }
//}
//
//@Composable
//fun LaporanList(
//    isContextLoading: Boolean,
//    laporansLoading: Boolean,
//    laporans: List<LaporanData>,
//    modifier: Modifier,
//    onLaporanClick: (LaporanData) -> Unit
//) {
//    if (laporansLoading) {
//        CommonProgressSpinner()
//    } else if (laporans.isEmpty()) {
//        Column(
//            modifier = modifier,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            if (!isContextLoading) Text(text = "Tidak ada Laporan")
//        }
//    } else {
//        LazyColumn(modifier = modifier) {
//
//            val rows = arrayListOf<LaporanRow>()
//            var currentRow = LaporanRow()
//            rows.add(currentRow)
//            for (laporan in laporans) {
//                if (currentRow.isfull()) {
//                    currentRow = LaporanRow()
//                    rows.add(currentRow)
//                }
//                currentRow.add(laporan = laporan)
//            }
//
//            items(items = rows) { row ->
//                LaporansRow(item = row, onLaporanClick = onLaporanClick)
//            }
//        }
//    }
//}
//
//@Composable
//fun LaporansRow(item: LaporanRow, onLaporanClick: (LaporanData) -> Unit) {
//    Row(modifier = Modifier
//        .fillMaxWidth()
//        .height(120.dp)) {
//        LaporanImage(imageUrl = item.laporan1?.laporanImage, modifier = Modifier
//            .weight(1f)
//            .clickable { item.laporan1?.let { laporan -> onLaporanClick(laporan) } })
//        LaporanImage(imageUrl = item.laporan2?.laporanImage, modifier = Modifier
//            .weight(1f)
//            .clickable { item.laporan2?.let { laporan -> onLaporanClick(laporan) } })
//        LaporanImage(imageUrl = item.laporan3?.laporanImage, modifier = Modifier
//            .weight(1f)
//            .clickable { item.laporan3?.let { laporan -> onLaporanClick(laporan) } })
//    }
//}
//
//@Composable
//fun LaporanImage(imageUrl: String?, modifier: Modifier) {
//    Box(modifier = modifier) {
//        var modifier = Modifier
//            .padding(1.dp)
//            .fillMaxSize()
//        if (imageUrl == null) {
//            modifier = modifier.clickable(enabled = false) {  }
//        }
//        CommonImage(data = imageUrl, modifier = modifier, contentScale = ContentScale.Crop)
//    }
//}