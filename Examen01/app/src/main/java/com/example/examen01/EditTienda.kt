package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditTienda : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tienda)

        val nombre = findViewById<EditText>(R.id.edit_input_nombre)
        val direccion = findViewById<EditText>(R.id.edit_input_direccion)
        val ciudad = findViewById<EditText>(R.id.edit_input_ciudad)
        val numEmpleados = findViewById<EditText>(R.id.edit_input_numEmpleados)

        val nombreT = intent.getStringExtra("nombre")
        val direccionT = intent.getStringExtra("direccion")
        val ciudadT = intent.getStringExtra("ciudad")
        val numEmpleadosT = intent.getIntExtra("numEmpleados", 0)


        nombre.setText(nombreT)
        direccion.setText(direccionT)
        ciudad.setText(ciudadT)
        numEmpleados.setText(numEmpleadosT.toString())

        val btn_cancel_edit = findViewById<Button>(R.id.btn_cancelaTienda)
        btn_cancel_edit
            .setOnClickListener {
                finish()
            }

        val btn_save_edit = findViewById<Button>(R.id.edit_btn_guardarTienda)
        btn_save_edit
            .setOnClickListener {

                if(nombre.text.isNotBlank() && direccion.text.isNotBlank() &&
                    ciudad.text.isNotBlank() && numEmpleados.text.isNotBlank()) {
                    val intentResp = Intent()
                    intentResp.putExtra("nombre", nombre.text.toString())
                    intentResp.putExtra("direccion", direccion.text.toString())
                    intentResp.putExtra("ciudad", ciudad.text.toString())
                    intentResp.putExtra("numEmpleados", numEmpleados.text.toString().toInt())
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