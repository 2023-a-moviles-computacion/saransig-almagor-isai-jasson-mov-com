package com.example.proyecto_iib

interface RopaCallback {
    fun onRopaObtenida(ropa: Ropa)
    fun onError(error: Exception)
}