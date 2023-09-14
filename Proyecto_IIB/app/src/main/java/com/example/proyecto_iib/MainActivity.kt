package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crearBD()
        val btn = findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
            irActividad(catalogoActivity::class.java)
        }


    }

    fun crearBD(){
        val db = Firebase.firestore
        val numbers = arrayListOf(1,2)
        var i = 0
        var c = 0
        val tipos = arrayListOf("camiseta", "saco","pantalones", "buzo","short","blusa","vestido","sudadera")
        val colecciones = arrayListOf("hombre", "mujer")
        val tiendaRef = db.collection("tienda")
            .whereEqualTo("nombre","Hub Style")
            .get()
            .addOnSuccessListener {
                documents ->
                for (document in documents){
                    for (tipo in tipos){
                        i = 0
                        for(coleccion in colecciones){
                            for (j in numbers){
                                i = i +1
                                c = c+1
                                val datosHombre = hashMapOf(
                                    "coleccion" to coleccion,
                                    "descripcion" to "",
                                    "detalle" to "",
                                    "imagen" to tipo+i,
                                    "precio" to 10.0,
                                    "tipo" to tipo
                                )
                                document.reference.collection("productos")
                                    .document("00"+c)
                                    .set(datosHombre)
                            }

                        }
                    }

                }
            }
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