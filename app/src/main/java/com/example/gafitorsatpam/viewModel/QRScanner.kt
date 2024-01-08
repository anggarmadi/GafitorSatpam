package com.example.gafitorsatpam.viewModel

import android.content.Context
import android.print.PrintJobInfo.STATE_CANCELED
import android.print.PrintJobInfo.STATE_COMPLETED
import android.print.PrintJobInfo.STATE_FAILED
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class QRScanner(appContext: Context) {
    val context = appContext
    private val opsi = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC)
        .build()

    private val pindai = GmsBarcodeScanning.getClient(appContext, opsi)
    val moduleInstallRequest =
        ModuleInstallRequest.newBuilder()
            .addApi(pindai) //Add the scanner client to the module install request
            .build()

    val moduleInstallClient = ModuleInstall.getClient(appContext)
    val hasilPindai = MutableStateFlow<String?>(null)


    suspend fun startScan(){
        try {
            moduleInstallClient
                .installModules(moduleInstallRequest)
//                .addOnSuccessListener {
//                    if (it.areModulesAlreadyInstalled()) {
//                        Toast.makeText(context, "Modules are already installed", Toast.LENGTH_LONG).show()
//                    }
////                    Toast.makeText(context, "Modules successfully installed", Toast.LENGTH_LONG).show()
//                }
                .addOnFailureListener {
                    Log.e("MainActivity", "Error installing modules", it)
                }
            pindai.startScan()
                .addOnSuccessListener { barcode ->
                    val noPlatRegex = Regex("^[A-Z]{1,2}\\s[0-9]{1,4}\\s[A-Z]{1,3}$")
                    if (barcode.displayValue.toString().matches(noPlatRegex)) {  // Gunakan objek Regex dalam matches()
                        // Hasil scan valid
                        hasilPindai.value = barcode.displayValue
                    } else {
                        // Hasil scan tidak valid
                        Toast.makeText(context, "No plat tidak valid", Toast.LENGTH_SHORT).show()
//                        hasilPindai.value = "No plat tidak valid"
                    }
                // Task completed successfully
//                    hasilPindai.value = barcode.displayValue
                }
                .addOnCanceledListener {
                    // Task canceled
//                    hasilPindai.value = "Batal"
                    Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
//                    hasilPindai.value = "Gagal"
                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()
                    Log.e("ErorQR","Error bre,, $e")
                }
        } catch (e: Exception){

        }

    }

}