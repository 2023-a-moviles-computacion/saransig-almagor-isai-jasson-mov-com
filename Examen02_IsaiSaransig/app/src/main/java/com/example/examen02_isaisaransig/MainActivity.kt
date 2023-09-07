package com.example.examen02_isaisaransig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        COFirebase.firebase = FirebaseSettings()
        val read = findViewById<Button>(R.id.btn_read)
        read
            .setOnClickListener {
                irActividad(ListTienda::class.java)
            }

    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}