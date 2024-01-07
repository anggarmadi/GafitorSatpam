package com.example.simpleqrscanner.ViewModel

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch
import androidx.activity.result.registerForActivityResult

class QRScanner(context: Context, barcodeLauncher: ActivityResultLauncher<ScanOptions>) {
    val barcodeLauncher = barcodeLauncher
        fun tampilkanKamera() {
            val options = ScanOptions()
            .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            .setPrompt("Scan the QR Code")
            .setCameraId(0)
            .setBeepEnabled(false)
            .setOrientationLocked(false)

            barcodeLauncher.launch(options)
    }
}