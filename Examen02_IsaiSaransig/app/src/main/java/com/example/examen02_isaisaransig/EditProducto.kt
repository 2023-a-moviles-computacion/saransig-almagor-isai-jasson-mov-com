package com.example.examen02_isaisaransig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class EditProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_producto)


        val descripcion = findViewById<EditText>(R.id.input_descripcion)
        val precio = findViewById<EditText>(R.id.input_precio)
        val descuento = findViewById<Switch>(R.id.input_descuento)
        val fechaElaboracion = findViewById<CalendarView>(R.id.input_fechaElaboración)


        val descripcionT = intent.getStringExtra("descripcion")
        val precioT = intent.getDoubleExtra("precio", 0.0)
        val descuentoT = intent.getIntExtra("descuento", 0)
        val fechaElaboracionT = intent.getLongExtra("fechaDeElaboracion", 0)


        descripcion.setText(descripcionT)
        precio.setText(precioT.toString())

        descuento.isChecked = if(descuentoT == 1) true else false

        fechaElaboracion.date = fechaElaboracionT


        fechaElaboracion.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            fechaElaboracion.date = calendar.timeInMillis
        }

        val btn_cancel_edit = findViewById<Button>(R.id.btn_cancelar)
        btn_cancel_edit
            .setOnClickListener {
                finish()
            }



        val btn_save_edit = findViewById<Button>(R.id.btn_guardarProducto)
        btn_save_edit
            .setOnClickListener {

                if(descripcion.text.isNotBlank() && precio.text.isNotBlank()){

                    val intentResp = Intent()
                    intentResp.putExtra("descripcion", descripcion.text.toString())
                    intentResp.putExtra("precio", precio.text.toString().toDouble())
                    intentResp.putExtra("descuento", if (descuento.isChecked) 1 else 0)
                    intentResp.putExtra("fechaDeElaboracion", fechaElaboracion.date.toLong())
                    setResult(
                        RESULT_OK,
                        intentResp
                    )
                    finish()
                }else{
                    val mensaje = "Debe llenar todos los campos"
                    val duracion = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, mensaje, duracion)
                    toast.show()
                }
            }
    }


}