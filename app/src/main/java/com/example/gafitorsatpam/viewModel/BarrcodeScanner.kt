package com.example.gafitorsatpam.viewModel

import android.content.Context
import android.os.Build
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarrcodeScanner(
    appContext: Context
) {
    val barcodeResult = MutableStateFlow<String?>(null)
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC)
        .build()
    val scanner = GmsBarcodeScanning.getClient(appContext, options)
    suspend fun startScan(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){



            try {
                scanner.startScan()
                    .addOnSuccessListener { barcode ->
                        //validasi hasil scan
//                        val noPlatRegex = Regex("^[A-Z]{1,2}\\s[0-9]{1,4}\\s[A-Z]{1,3}")
//                        if (barcodeResult != null){
//                            if (!barcodeResult.toString().matches(noPlatRegex)){
//                                barcodeResult.value = "No plat tidak valid"
//                            }
//                        } else{
//                            barcodeResult.value = barcode.displayValue
//                        }
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

//        } else{
//
//        }

    }
}