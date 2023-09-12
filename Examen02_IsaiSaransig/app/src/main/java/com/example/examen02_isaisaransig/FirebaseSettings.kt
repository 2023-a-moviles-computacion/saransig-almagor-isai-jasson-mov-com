package com.example.examen02_isaisaransig

import android.annotation.SuppressLint
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList
import android.widget.EditText


class FirebaseSettings {
    companion object {
        var arregloTiendas = ArrayList<Tienda>()
        var arregloProductos = ArrayList<Producto>()
    }


    fun createTiendas(tienda: Tienda){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("tienda")
        val datosEstudiante = hashMapOf(
            "nombre" to tienda.nombre,
            "direccion" to tienda.direccion,
            "ciudad" to tienda.ciudad,
            "numeroEmpleados" to tienda.numeroEmpleados
        )
        referenciaEjemploEstudiante.add(datosEstudiante)

    }

    fun readTiendas(adaptador: ArrayAdapter<Tienda>) {
        arregloTiendas.clear()
        val db = Firebase.firestore
        val tiendasRef = db.collection("tienda")
        tiendasRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val tienda = Tienda()
                    tienda.nombre = document.getString("nombre").toString()
                    tienda.direccion = document.getString("direccion").toString()
                    tienda.ciudad = document.getString("ciudad").toString()
                    tienda.numeroEmpleados = document.get("numeroEmpleados").toString().toInt()
                    arregloTiendas.add(tienda)
                }
                // Notificar al adaptador que los datos han cambiado
                adaptador.notifyDataSetChanged()
            }

    }

    fun deleteTienda(nombre: String?, adaptador: ArrayAdapter<Tienda>){
        val db = Firebase.firestore
        val tiendaRef = db
            .collection("tienda")

        tiendaRef
            .whereEqualTo("nombre", nombre)
            .get()
            .addOnSuccessListener {
                documents ->
                for (document in documents){
                    document.reference.delete()
                }

                    // Elimina el elemento del arreglo y notifica al adaptador
                    val index = arregloTiendas.indexOfFirst { it.nombre == nombre }
                    if (index != -1) {
                        arregloTiendas.removeAt(index)
                        adaptador.notifyDataSetChanged()
                    }

            }


    }

    fun updateTienda(nombre: String?, direccion: String?, ciudad:String?, numeroEmpleados:Int? ,adaptador: ArrayAdapter<Tienda>){

        val db = Firebase.firestore
        val datosUpdate = hashMapOf(
            "nombre" to nombre as Any,
            "direccion"  to direccion as Any,
            "ciudad" to ciudad as Any,
            "numeroEmpleados" to numeroEmpleados as Any
        )
        val tiendaRef = db
            .collection("tienda")
        tiendaRef
            .whereEqualTo("nombre", nombre)
            .get()
            .addOnSuccessListener {
                documents ->
                for (document in documents){
                    document.reference.update(datosUpdate)
                        .addOnSuccessListener {
                            val index = arregloTiendas.indexOfFirst { it.nombre == nombre }
                            if (index != -1) {
                                arregloTiendas[index].nombre = nombre
                                arregloTiendas[index].direccion = direccion
                                arregloTiendas[index].ciudad = ciudad
                                arregloTiendas[index].numeroEmpleados = numeroEmpleados
                                adaptador.notifyDataSetChanged()
                            }

                        }
                }
            }

    }

    //PRODUCTOS

    @SuppressLint("SuspiciousIndentation")
    fun readProductos(tienda:String, adaptador: ArrayAdapter<Producto>){
        arregloProductos.clear()
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")
            tiendaRef.whereEqualTo("nombre", tienda)
                .get()
                .addOnSuccessListener {
                    documents ->
                    for (document in documents){
                        val productosRef = document.reference.collection("productos")

                        productosRef.get()
                            .addOnSuccessListener { productos ->
                                for (producto in productos) {
                                    var descripcion = producto.getString("descripcion").toString()
                                    var fechaDeElaboracion = producto.getLong("fechaDeElaboracion").toString().toLong()
                                    var precio = producto.getDouble("precio").toString().toDouble()
                                    var descuento = producto.get("descuento").toString().toInt()
                                    var product = Producto(descripcion, fechaDeElaboracion, precio, descuento)
                                    arregloProductos.add(product)
                                }
                                adaptador.notifyDataSetChanged()
                            }
                    }
                }
    }



    fun insertProducto(tienda:String, adaptador: ArrayAdapter<Producto>, producto: Producto){
        val db = Firebase.firestore
        val tiendaRef = db
            .collection("tienda")
            .whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener{
                documents ->
                for (document in documents){
                    val datosProducto = hashMapOf(
                        "descripcion" to producto.descripcion,
                        "fechaDeElaboracion" to producto.fechaDeElaboracion,
                        "precio" to producto.precio,
                        "descuento" to producto.descuento
                    )
                    document.reference.collection("productos").add(datosProducto)
                        .addOnSuccessListener {
                            arregloProductos.add(producto)
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }




}