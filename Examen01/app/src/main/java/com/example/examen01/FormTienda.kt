package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormTienda : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tienda)

        val btn_cancel = findViewById<Button>(R.id.btn_cancelaTienda)
        btn_cancel
            .setOnClickListener {
                finish()
            }

        var btnCreate = findViewById<Button>(R.id.edit_btn_guardarTienda)
        btnCreate
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.edit_input_nombre)
                val direccion = findViewById<EditText>(R.id.edit_input_direccion)
                val ciudad = findViewById<EditText>(R.id.edit_input_ciudad)
                val numEmpleados = findViewById<EditText>(R.id.edit_input_numEmpleados)

                if(nombre.text.isNotBlank() && direccion.text.isNotBlank() &&
                    ciudad.text.isNotBlank() && numEmpleados.text.isNotBlank()){

                val intentInsert = Intent()
                intentInsert.putExtra("nombre", nombre.text.toString())
                intentInsert.putExtra("direccion", direccion.text.toString())
                intentInsert.putExtra("ciudad", ciudad.text.toString())
                intentInsert.putExtra("numEmpleados", numEmpleados.text.toString().toInt())
                setResult(
                    RESULT_OK,
                    intentInsert
                )

                val mensaje = "Tienda ingresado correctamente"
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