package com.example.proyecto_iib

import android.widget.ArrayAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirestoreDB {
    companion object{
        var arregloDestacados = ArrayList<Ropa>()
        var arregloRopa = ArrayList<Ropa>()
        var arregloCarrito = ArrayList<Carrito>()
        var arregloPedido = ArrayList<Pedido>()
    }

    fun readDestacados(tienda:String, adaptador: ropaAdapter){
        arregloDestacados.clear()
        val db = Firebase.firestore


        val tiendaRef = db.collection("tienda")
            .whereEqualTo("nombre","HubStyle")
            .get()
            .addOnSuccessListener {
                    documents ->
                for (document in documents){
                    val ropaRef = document.reference.collection("productos")

                    ropaRef.get()
                        .addOnSuccessListener { productos ->
                            for (ropa in productos) {
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val puntaje = ropa.get("puntaje").toString().toInt()
                                val img = ropa.get("imagen").toString()
                                if(puntaje > 3){
                                    var producto = Ropa(descripcion, precio, coleccion, tipo, img)
                                    arregloDestacados.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }



    fun readColeccion(tienda:String, coleccionR:String, adaptador: ropaAdapter){
        arregloDestacados.clear()
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")
        tiendaRef.whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener {
                    documents ->
                for (document in documents){
                    val ropaRef = document.reference.collection("productos")

                    ropaRef.get()
                        .addOnSuccessListener { productos ->
                            for (ropa in productos) {
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()
                                if(coleccionR == coleccion){
                                    var producto = Ropa(descripcion, precio, coleccion, tipo, img)
                                    arregloRopa.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }

    fun readTipo(tienda:String, tipoR:String, adaptador: ropaAdapter){
        arregloDestacados.clear()
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")
        tiendaRef.whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener {
                    documents ->
                for (document in documents){
                    val ropaRef = document.reference.collection("productos")

                    ropaRef.get()
                        .addOnSuccessListener { productos ->
                            for (ropa in productos) {
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()
                                if(tipoR == tipo){
                                    var producto = Ropa(descripcion, precio, coleccion, tipo, img)
                                    arregloRopa.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }



}