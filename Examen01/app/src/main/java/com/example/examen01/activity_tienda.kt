package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class activity_tienda : AppCompatActivity() {
    val bd = BDExamen(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)
    }

    fun guardarTienda(view: View) {
        val tienda = Tienda()
        bd.insertTienda(tienda)
    }
}