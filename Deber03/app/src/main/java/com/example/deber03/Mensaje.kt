package com.example.deber03

import java.util.Date

class Mensaje(
    val nombrePersona: String,
    val mensaje: String,
    val fechaEnvio: Date,
    val horaEnvio: String,
    var visto: Boolean,
    var imgPersona: String
) {
    fun marcarComoVisto() {
        visto = true
    }
}
