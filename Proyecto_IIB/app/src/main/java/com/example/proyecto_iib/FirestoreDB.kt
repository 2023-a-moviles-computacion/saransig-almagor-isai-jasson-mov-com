package com.example.proyecto_iib

import android.widget.ArrayAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirestoreDB {
    companion object{
        var arregloRopa = arrayListOf<Ropa>()
    }

    fun readProductos(tienda:String){
        arregloRopa.clear()
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")
        tiendaRef.whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener {
                    documents ->
                for (document in documents){
                    val ropaRef = document.reference.collection("coleccion_ropa")

                    ropaRef.get()
                        .addOnSuccessListener { productos ->
                            for (ropa in productos) {
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                var producto = Ropa(descripcion, precio, coleccion, tipo)
                                arregloRopa.add(producto)
                            }
                            //adaptador.notifyDataSetChanged()
                        }
                }
            }
    }


}