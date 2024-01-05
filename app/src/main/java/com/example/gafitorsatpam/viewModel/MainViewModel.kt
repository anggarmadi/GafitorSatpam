package com.example.simpleqrscanner.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gafitorsatpam.model.ScannerState
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class MainViewModel(context: Context): ViewModel() {
    lateinit var scanner: GmsBarcodeScanner

    init {
        val options = GmsBarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS).build()
        scanner = GmsBarcodeScanning.getClient(context, options)
    }
    private val _state = MutableStateFlow(ScannerState())
    val state = _state.asStateFlow()

    fun startScanning(){
        viewModelScope.launch{
            startScanningFlow().collect{ data ->
                if(!data.isNullOrBlank()){
                    _state.value = state.value.copy(
                        details = data
                    )
                }
            }
        }
    }

    fun startScanningFlow(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    launch {
                        send(getDetails(barcode))
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }

            awaitClose{}
        }
    }

    private fun getDetails(barcode: Barcode): String{
        return when(barcode.valueType){
            Barcode.TYPE_URL -> {
                "url: ${barcode.url!!.url}"
            }
            else -> {
                barcode.rawValue ?: "only url is accepted"
            }
        }
    }
}