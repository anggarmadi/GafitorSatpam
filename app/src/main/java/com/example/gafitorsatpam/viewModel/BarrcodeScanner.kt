package com.example.gafitorsatpam.viewModel

import android.content.Context
import android.os.Build
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarrcodeScanner(
    appContext: Context
) {
    val appContext = appContext
    val barcodeResult = MutableStateFlow<String?>(null)
    suspend fun startScan(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val options = GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE,
                    Barcode.FORMAT_AZTEC)
                .build()

            val scanner = GmsBarcodeScanning.getClient(appContext, options)
            try {
                scanner.startScan()
                    .addOnSuccessListener { barcode ->
                        barcodeResult.value = barcode.displayValue
                    }
                    .addOnCanceledListener {
                        barcodeResult.value = "Dibatalkan"
                    }
                    .addOnFailureListener {
                        barcodeResult.value = "Gagal"
                    }
            } catch (e: Exception){

            }

        } else{

        }

    }
}