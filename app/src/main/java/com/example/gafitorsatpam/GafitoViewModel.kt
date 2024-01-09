package com.example.gafitorsatpam

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.Role
import androidx.lifecycle.ViewModel
import com.example.gafitorsatpam.data.Event
import com.example.gafitorsatpam.data.LaporanData
import com.example.gafitorsatpam.data.ParkirData
import com.example.gafitorsatpam.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.lang.invoke.TypeDescriptor
import java.util.UUID
import javax.inject.Inject

const val USERS = "users"
const val LAPORAN = "laporan"
const val PARKIR = "parkir"
const val HISTORY = "history"

@HiltViewModel
class GafitoViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {

    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    val refreshLaporanProgress = mutableStateOf(false)
    val laporans = mutableStateOf<List<LaporanData>>(listOf())
    val laporanAja = mutableStateOf<LaporanData?>(null)

    val parkirProgress = mutableStateOf(false)
    val parkirs = mutableStateOf<List<ParkirData>>(listOf())


    init {
//        auth.signOut()
        val currentUser = auth.currentUser
        signedIn.value = currentUser != null
        currentUser?.uid?.let { uid ->
            getUserData(uid)
        }
    }

    fun onSignup(username: String, email: String, pass: String) {
        if (username.isEmpty() or email.isEmpty() or pass.isEmpty()) {
            handleException(customMessage = "Please fill in all fields")
            return
        }
        inProgress.value = true

        db.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.size() > 0) {
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                signedIn.value = true
                                createOrUpdateProfile(username = username)
                            } else {
                                handleException(task.exception, "Sign Up Failed")
                            }
                            inProgress.value = false
                        }
                }
            }
            .addOnFailureListener { }
    }

    fun onLogin(email: String, pass: String) {
        if (email.isEmpty() or pass.isEmpty()) {
            handleException(customMessage = "Please fill in all fields")
            return
        }
        inProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.uid?.let { uid ->
                        inProgress.value = false
                        getUserData(uid)
                        // Cek role setelah login berhasil
                        db.collection(USERS).document(uid).get()
                            .addOnSuccessListener {
                                inProgress.value = true
                                val user = it.toObject<UserData>()
                                if (user?.role == "satpam") {
                                    signedIn.value = true
                                    inProgress.value = false
                                    // Role valid, lanjutkan navigasi
                                    handleException(customMessage = "Login Success")
                                } else {
                                    inProgress.value = false
                                    // Role tidak valid, logout pengguna
                                    handleException(customMessage = "Login gagal: Role tidak valid")
                                    auth.signOut()
                                }
                            }
                            .addOnFailureListener { exc ->
                                handleException(exc, "Cannot retrieve user data")
                                inProgress.value = false
                            }
                    }
                } else {
                    handleException(task.exception, "Login Failed")
                    inProgress.value = false
                }
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Login Failed")
                inProgress.value = false
            }
    }

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String? = null,
        imageUrl: String? = null,
        noPolisi: String? = null,
        jenisMotor: String? = null,
        noHP: String? = null,
    ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            username = username ?: userData.value?.username,
            imageUrl = imageUrl ?: userData.value?.imageUrl,
            noPolisi = noPolisi ?: userData.value?.noPolisi,
            jenisMotor = jenisMotor ?: userData.value?.jenisMotor,
            noHP = noHP ?: userData.value?.noHP
        )

        uid?.let { uid ->
            inProgress.value = true
            db.collection(USERS).document(uid).get().addOnSuccessListener {
                if (it.exists()) {
                    it.reference.update(userData.toMap())
                        .addOnSuccessListener {
                            this.userData.value = userData
                            inProgress.value = false
                        }
                        .addOnFailureListener {
                            handleException(it, "Cannot update user")
                            inProgress.value = false
                        }
                } else {
                    db.collection(USERS).document(uid).set(userData)
                    getUserData(uid)
                    inProgress.value = false
                }
            }
                .addOnFailureListener { exc ->
                    handleException(exc, "Cannot create User")
                    inProgress.value = false
                }
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USERS).document(uid).get()
            .addOnSuccessListener {
                val user = it.toObject<UserData>()
                userData.value = user
                inProgress.value = false
                refreshLaporan()
                refreshParkir()
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Cannot retrieve user data")
                inProgress.value = false
            }

    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }

    fun uploadImage(uri: Uri, onSuccess: (Uri) -> Unit) {
        inProgress.value = true

        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val imageRef = storageRef.child("image/$uuid")
        val uploadTask = imageRef.putFile(uri)

        uploadTask
            .addOnSuccessListener {
                val result = it.metadata?.reference?.downloadUrl
                result?.addOnSuccessListener(onSuccess)
            }
            .addOnFailureListener { exc ->
                handleException(exc)
                inProgress.value = false
            }
    }

    fun onLogout() {
        auth.signOut()
        signedIn.value = false
        userData.value = null
        popupNotification.value = Event("Logged Out")
    }

    fun onNewLaporan(
        uri: Uri,
        nomorPolisi: String,
        merek: String,
        warna: String,
        description: String,
        onLaporanSuccess: () -> Unit
    ) {
        uploadImage(uri) {
            onCreateLaporan(it, nomorPolisi, merek, warna, description, onLaporanSuccess)
        }
    }

    fun onCreateLaporan(
        imageUri: Uri,
        nomorPolisi: String,
        merek: String,
        warna: String,
        description: String,
        onLaporanSuccess: () -> Unit
    ) {
        inProgress.value = true
        val currenUid = auth.currentUser?.uid
        val currentName = userData.value?.name

        if (currenUid != null) {

            val laporanUuid = UUID.randomUUID().toString()

            val laporan = LaporanData(
                laporanId = laporanUuid,
                userId = currenUid,
                name = currentName,
                laporanImage = imageUri.toString(),
                nomorPolisi = nomorPolisi,
                merek = merek,
                warna = warna,
                description = description,
                time = System.currentTimeMillis()
            )

            db.collection(LAPORAN).document(laporanUuid).set(laporan)
                .addOnSuccessListener {
                    popupNotification.value = Event("Berhasil membuat laporan")
                    inProgress.value = false
                    refreshLaporan()
                    onLaporanSuccess.invoke()
                }
                .addOnFailureListener { exc ->
                    handleException(exc, "Gagal membuat laporan")
                    inProgress.value = false
                }

        } else {
            handleException(customMessage = "Gagal membuat laporan")
            onLogout()
            inProgress.value = false
        }
    }

    fun onUpdateLaporan(
        laporanId: String,
        uri: Uri,
        nomorPolisi: String,
        merek: String,
        warna: String,
        description: String,
        onLaporanSuccess: () -> Unit
    ) {
        uploadImage(uri) {
            onEditLaporan(laporanId, it, nomorPolisi, merek, warna, description, onLaporanSuccess)
        }
    }

    fun onEditLaporan(
        laporanId: String,
        imageUri: Uri,
        nomorPolisi: String,
        merek: String,
        warna: String,
        description: String,
        onLaporanSuccess: () -> Unit
    ) {
        inProgress.value = true
        val currenUid = auth.currentUser?.uid
        val currentName = userData.value?.name

        if (currenUid != null) {
            val laporan = LaporanData(
                laporanId = laporanId,
                userId = currenUid,
                name = currentName,
                laporanImage = imageUri.toString(),
                nomorPolisi = nomorPolisi,
                merek = merek,
                warna = warna,
                description = description,
                time = System.currentTimeMillis()
            )

            db.collection(LAPORAN).document(laporanId).set(laporan)
                .addOnSuccessListener {
                    popupNotification.value = Event("Berhasil update laporan")
                    inProgress.value = false
                    refreshLaporan()
                    onLaporanSuccess.invoke()
                }
                .addOnFailureListener { exc ->
                    handleException(exc, "Gagal update laporan")
                    inProgress.value = false
                }
        } else {
            handleException(customMessage = "Gagal update laporan")
            onLogout()
            inProgress.value = false
        }
    }

    fun onDeleteLaporan(laporanId: String, onLaporanSuccess: () -> Unit) {
            // Tidak ada gambar, langsung hapus laporan
            deleteLaporanFromDatabase(laporanId, onLaporanSuccess)
        refreshLaporan()
    }

    private fun deleteLaporanFromDatabase(laporanId: String, onLaporanSuccess: () -> Unit) {
        inProgress.value = true
        // 2. Hapus laporan dari database
        db.collection(LAPORAN).document(laporanId)
            .delete()
            .addOnSuccessListener {
                inProgress.value = false
                // Tampilkan pesan sukses penghapusan
                popupNotification.value = Event("Data Berhasil dihapus")
                onLaporanSuccess.invoke()
            }
            .addOnFailureListener {
                inProgress.value = false
                // Handle error while deleting report
                Log.e("GafitoViewModel", "Error deleting report: $it")
                handleException(customMessage = "Terjadi kesalahan")
            }
    }


    fun refreshLaporan() {
        val currentUid = auth.currentUser?.uid

        if (currentUid != null) {
            refreshLaporanProgress.value = true
            db.collection(LAPORAN).get()
                .addOnSuccessListener { documents ->
                    convertLaporan(documents, laporans)
                    refreshLaporanProgress.value = false
                }
                .addOnFailureListener { exc ->
                    handleException(exc, "Gagal mengambil laporan")
                    refreshLaporanProgress.value = false
                }
        } else {
            handleException(customMessage = "Error! Gagal memuat laporan")
        }
    }

    private fun convertLaporan(documents: QuerySnapshot, outState: MutableState<List<LaporanData>>) {
        val newLaporans = mutableListOf<LaporanData>()
        documents.forEach { doc ->
            val laporan = doc.toObject<LaporanData>()
            newLaporans.add(laporan)
        }
        val sortedLaporans = newLaporans.sortedByDescending { it.time }
        outState.value = sortedLaporans
    }


    // Fungsi untuk membuat collection parkir berdasarkan data nomorPolisi
    fun onCreateParkir(noPolisi: String) {
        if (noPolisi.isEmpty()) {
            handleException(customMessage = "Please fill in all fields")
            return
        } else {

            inProgress.value = true
            val parkirData = ParkirData()

            parkirData.parkirId = UUID.randomUUID().toString()
            parkirData.noPolisi = noPolisi
            parkirData.time = System.currentTimeMillis()
            val parkirRef = db.collection(PARKIR).whereEqualTo("noPolisi", noPolisi)
            parkirRef.get().addOnSuccessListener { querySnapshot ->
                // Jika data parkir ditemukan, maka pindahkan data tersebut ke collection history
                if (querySnapshot.size() > 0) {
                    val parkir = querySnapshot.documents[0].toObject(ParkirData::class.java)
                    // Membuat dokumen baru di collection history dengan data parkir yang sama
                    db.collection(HISTORY).document(parkir?.parkirId!!).set(parkir)
                        .addOnSuccessListener {
                            // Tampilkan pesan sukses jika berhasil memindahkan data parkir ke collection history
                            popupNotification.value = Event("Data Parkir berhasil dipindahkan ke history")
                            inProgress.value = false
                            refreshParkir()
                        }
                        .addOnFailureListener { exc ->
                            // Tampilkan pesan error jika gagal memindahkan data parkir ke collection history
                            handleException(exc, "Gagal memindahkan data Parkir")
                            inProgress.value = false
                        }
                    // Menghapus dokumen lama di collection parkir dengan data parkir yang sama
                    db.collection(PARKIR).document(parkir.parkirId!!).delete()
                        .addOnSuccessListener {
                            // Tampilkan pesan sukses jika berhasil menghapus data parkir dari collection parkir
                            popupNotification.value = Event("Data Parkir berhasil dihapus")
                            inProgress.value = false
                        }
                        .addOnFailureListener { exc ->
                            // Tampilkan pesan error jika gagal menghapus data parkir dari collection parkir
                            handleException(exc, "Gagal menghapus data Parkir")
                            inProgress.value = false
                        }
                } else {
                    val userRef = db.collection(USERS).whereEqualTo("noPolisi", noPolisi)
                    userRef.get().addOnSuccessListener { querySnapshot ->
                        // Jika data user ditemukan, mengisi data userId, merek, name, dan imageUrl dari user
                        if (querySnapshot.size() > 0) {
                            val user = querySnapshot.documents[0].toObject(UserData::class.java)
                            parkirData.userId = user?.userId
                            parkirData.merek = user?.jenisMotor
                            parkirData.name = user?.name
                            parkirData.imageUrl = user?.imageUrl

                            // Menyimpan data parkir ke collection parkir
                            db.collection(PARKIR).document(parkirData.parkirId!!).set(parkirData)
                                .addOnSuccessListener {
                                    refreshParkir()
                                    // Tampilkan pesan sukses jika berhasil menyimpan data parkir
                                    popupNotification.value = Event("Data Parkir berhasil disimpan")
                                    inProgress.value = false
                                }
                                .addOnFailureListener { exc ->
                                    // Tampilkan pesan error jika gagal menyimpan data parkir
                                    handleException(exc, "Gagal menyimpan data Parkir")
                                    inProgress.value = false
                                }
                        } else {
                            // Jika data user tidak ditemukan, tampilkan pesan error dan batalkan proses penyimpanan data parkir
                            popupNotification.value = Event("Data User Tidak Ditemukan")
                            return@addOnSuccessListener
                        }
                    }
                        .addOnFailureListener { exc ->
                            // Tampilkan pesan error jika gagal mencari data user
                            handleException(exc, "Gagal mencari data User")
                            inProgress.value = false
                        }
                }
            }
                .addOnFailureListener { exc ->
                    // Tampilkan pesan error jika gagal mencari data parkir
                    handleException(exc, "Gagal mencari data Parkir")
                    inProgress.value = false
                }
        }
    }

    fun refreshParkir() {
        val currentUid = auth.currentUser?.uid

        if (currentUid != null) {
            parkirProgress.value = true
            db.collection(PARKIR).get()
                .addOnSuccessListener { documents ->
                    convertParkir(documents, parkirs)
                    parkirProgress.value = false
                }
                .addOnFailureListener { exc ->
                    handleException(exc, "Gagal mengambil data parkir")
                    parkirProgress.value = false
                }
        } else {
            handleException(customMessage = "Error! Gagal memuat data parkir")
        }
    }

    private fun convertParkir(documents: QuerySnapshot, outState: MutableState<List<ParkirData>>) {
        val newParkir = mutableListOf<ParkirData>()
        documents.forEach { doc ->
            val parkir = doc.toObject<ParkirData>()
            newParkir.add(parkir)
        }
        val sortedLaporans = newParkir.sortedByDescending { it.time }
        outState.value = sortedLaporans
    }

}