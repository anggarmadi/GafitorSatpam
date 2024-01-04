package com.example.gafitorsatpam.data

data class UserData (
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var noPolisi: String? = null,
    var jenisMotor: String? = null,
    var noHP: String? = null,
    var role: String? = "satpam"
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageUrl" to imageUrl,
        "noPolisi" to noPolisi,
        "jenisMotor" to jenisMotor,
        "noHP" to noHP,
        "role" to role
    )
}