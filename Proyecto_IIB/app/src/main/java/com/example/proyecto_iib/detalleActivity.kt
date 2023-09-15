package com.example.proyecto_iib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class detalleActivity : AppCompatActivity() {
    var codigo=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        codigo = intent.getStringExtra("codigo").toString()
        COFirebase.firebase!!.getRopa(codigo, object : RopaCallback {
            override fun onRopaObtenida(ropa: Ropa) {
                var tipo_detalle = findViewById<TextView>(R.id.tv_tipo_detalle)
                var precio = findViewById<TextView>(R.id.tv_precio_detalle)
                var detalle = findViewById<TextView>(R.id.tv_detalle_detalle)
                var ropaImg = findViewById<ImageView>(R.id.img_detalle)
                tipo_detalle.text = ropa.tipo
                precio.text = "USD " + ropa.precio
                detalle.text = ropa.detalle

                val imageResource = view.itemView.context.resources.getIdentifier(
                    ropaActual.imagen,
                    "drawable",
                    holder.itemView.context.packageName
                )

                holder.ropaImg.setImageResource(imageResource)

            }

            override fun onError(error: Exception) {
                // Manejar errores si ocurrieron
            }
        })

    }
}