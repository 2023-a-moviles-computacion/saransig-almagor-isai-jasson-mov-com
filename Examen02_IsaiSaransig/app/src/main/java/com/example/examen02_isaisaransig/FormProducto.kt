package com.example.examen02_isaisaransig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class FormProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_producto)

        val fechaElab = findViewById<CalendarView>(R.id.input_fechaElaboración)
        fechaElab.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            fechaElab.date = calendar.timeInMillis
        }


        val btn_cancel = findViewById<Button>(R.id.btn_cancelar)
        btn_cancel
            .setOnClickListener {
                finish()
            }

        var btnCreate = findViewById<Button>(R.id.btn_guardarProducto)
        btnCreate
            .setOnClickListener {
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val precio = findViewById<EditText>(R.id.input_precio)
                val descuento = findViewById<Switch>(R.id.input_descuento)

                if(descripcion.text.isNotBlank() && precio.text.isNotBlank()){
                    val intentInsert = Intent()
                    intentInsert.putExtra("descripcion", descripcion.text.toString())
                    intentInsert.putExtra("precio", precio.text.toString().toDouble())


                    var descuentoV = 0
                    val switchSelected = descuento.isChecked
                    if (switchSelected){
                        descuentoV = 1
                    }
                    intentInsert.putExtra("descuento", descuentoV)
                    intentInsert.putExtra("fechaElab", fechaElab.date.toLong())
                    setResult(
                        RESULT_OK,
                        intentInsert
                    )
                    val mensaje = "Producto ingresado correctamente"
                    val duracion = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this, mensaje, duracion)
                    toast.show()
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