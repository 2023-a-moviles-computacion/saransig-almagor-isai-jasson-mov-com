package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        COFirebase.firebase = FirestoreDB()
        crearBD()
        val btn = findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
            irActividad(catalogoActivity::class.java)
        }


    }

    fun puntajeRand(): Int {
        return Random.nextInt(2, 6) // Genera un número aleatorio entre 3 y 5 (incluidos)
    }
    fun crearBD(){
        val db = Firebase.firestore
        val numbers = arrayListOf(1,2)
        var i = 0
        var c = 0
        val tipos = arrayListOf("camiseta", "saco","pantalones", "buzo","short","blusa","vestido","sudadera")
        val colecciones = arrayListOf("hombre", "mujer")
        val tiendaRef = db.collection("tienda")
            .whereEqualTo("nombre","HubStyle")
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
                                var precio = 0.0
                                when(tipo){
                                    "camiseta" -> precio = 10.0
                                    "saco" -> precio = 15.0
                                    "pantalones" -> precio = 25.0
                                    "buzo" -> precio = 8.0
                                    "short" -> precio = 5.0
                                    "blusa" -> precio = 7.0
                                    "vestido" -> precio = 19.50
                                    "sudadera" -> precio = 18.0
                                }
                                val datosHombre = hashMapOf(
                                    "coleccion" to coleccion,
                                    "descripcion" to "",
                                    "detalle" to "",
                                    "imagen" to "camiseta1",
                                    "precio" to precio,
                                    "tipo" to tipo,
                                    "puntaje" to puntajeRand()
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