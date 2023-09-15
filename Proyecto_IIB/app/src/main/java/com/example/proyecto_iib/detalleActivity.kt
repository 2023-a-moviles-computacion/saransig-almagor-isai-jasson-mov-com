package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class detalleActivity : AppCompatActivity() {
    var codigo=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        val btn_catalogo = findViewById<ImageButton>(R.id.ibtn_catalogo)
        btn_catalogo.setOnClickListener {
            irActividad(catalogoActivity::class.java)
        }
        codigo = intent.getStringExtra("codigo").toString()
        COFirebase.firebase!!.getRopa(codigo, object : RopaCallback {
            override fun onRopaObtenida(ropa: Ropa) {
                var tipo_detalle = findViewById<TextView>(R.id.tv_tipo_detalle)
                var precio = findViewById<TextView>(R.id.tv_precio_detalle)
                var detalle = findViewById<TextView>(R.id.tv_detalle_detalle)
                var ropaImg = findViewById<ImageView>(R.id.img_detalle)

                tipo_detalle.text = ropa.tipo
                precio.text = ropa.precio.toString()
                detalle.text = ropa.detalle.toString()
                val imagenResourceId = resources.getIdentifier(ropa.imagen, "drawable", packageName)

                if (imagenResourceId != 0) {
                    ropaImg.setImageResource(imagenResourceId)
                }


            }

            override fun onError(error: Exception) {
                // Manejar errores si ocurrieron
            }
        })

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }
}