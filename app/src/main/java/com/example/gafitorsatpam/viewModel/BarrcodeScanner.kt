package com.example.gafitorsatpam.viewModel

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarrcodeScanner(
    appContext: Context
) {
    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC)
        .build()

    private val scanner = GmsBarcodeScanning.getClient(appContext, options)
    val barcodeResult = MutableStateFlow<String?>(null)

    suspend fun startScan(){
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
    }
}