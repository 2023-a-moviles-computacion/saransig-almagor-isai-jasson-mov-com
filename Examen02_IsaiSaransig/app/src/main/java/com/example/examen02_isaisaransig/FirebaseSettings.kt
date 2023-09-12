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
        val tiendaRef = db
            .collection("tienda")
        val datosTienda = hashMapOf(
            "nombre" to tienda.nombre,
            "direccion" to tienda.direccion,
            "ciudad" to tienda.ciudad,
            "numeroEmpleados" to tienda.numeroEmpleados
        )
        tiendaRef.add(datosTienda)

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

    fun deleteProducto(tienda: String, adaptador: ArrayAdapter<Producto>, descripcion: String?){
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")
        tiendaRef.whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener {
                documents ->
                for (document in documents){
                    var productoRef = document.reference.collection("productos")
                    productoRef.whereEqualTo("descripcion", descripcion)
                        .get()
                        .addOnSuccessListener {
                            productos ->
                            for (producto in productos){
                                producto.reference.delete()
                            }

                            // Elimina el elemento del arreglo y notifica al adaptador
                            val index = arregloProductos.indexOfFirst { it.descripcion == descripcion }
                            if (index != -1) {
                                arregloProductos.removeAt(index)
                                adaptador.notifyDataSetChanged()
                            }
                        }
                }
            }
    }

    fun updateProducto(tienda: String, adaptador: ArrayAdapter<Producto>, producto: Producto){
        val db = Firebase.firestore
        val datosUpdate = hashMapOf(
            "descripcion" to producto.descripcion as Any,
            "fechaDeElaboracion" to producto.fechaDeElaboracion as Any,
            "precio" to producto.precio as Any,
            "descuento" to producto.descuento as Any
        )

        val tiendaRef = db.collection("tienda")
        tiendaRef.whereEqualTo("nombre", tienda)
            .get()
            .addOnSuccessListener {
                documents ->
                for(document in documents){
                    val productoRef = document.reference.collection("productos")
                    productoRef.whereEqualTo("descripcion", producto.descripcion)
                        .get()
                        .addOnSuccessListener {
                            productos ->
                            for (product in productos){
                                product.reference.update(datosUpdate)
                                    .addOnSuccessListener {
                                        val index = arregloProductos.indexOfFirst { it.descripcion == producto.descripcion}
                                        if (index != -1) {
                                            arregloProductos[index].fechaDeElaboracion = producto.fechaDeElaboracion
                                            arregloProductos[index].precio = producto.precio
                                            arregloProductos[index].descuento = producto.descuento
                                            adaptador.notifyDataSetChanged()
                                        }

                                    }
                            }
                        }
                }
            }
    }

}