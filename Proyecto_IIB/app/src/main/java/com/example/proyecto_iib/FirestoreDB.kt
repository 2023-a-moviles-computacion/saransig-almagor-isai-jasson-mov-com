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
        var arregloCamiseta = ArrayList<Ropa>()
        var arregloPantalones = ArrayList<Ropa>()
        var arregloChaqueta = ArrayList<Ropa>()
        var arregloHoodies = ArrayList<Ropa>()
        var arregloHombre =  ArrayList<Ropa>()
        var arregloMujer =  ArrayList<Ropa>()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val puntaje = ropa.get("puntaje").toString().toInt()
                                val img = ropa.get("imagen").toString()
                                if(puntaje > 3){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                    arregloDestacados.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }



    fun readColeccionCamiseta(tienda:String, adaptador: ropaAdapter, coleccionR: String){
        arregloCamiseta.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()

                                if(coleccionR == coleccion && tipo == "camiseta"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                    arregloCamiseta.add(producto)

                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }
    fun readColeccionPantalon(tienda:String, adaptador: ropaAdapter, coleccionR: String){
        arregloPantalones.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()

                                if(coleccionR == coleccion && tipo=="pantalon"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                        arregloPantalones.add(producto)

                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }
    fun readColeccionChaqueta(tienda:String, adaptador: ropaAdapter, coleccionR: String){
        arregloChaqueta.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()

                                if(coleccionR == coleccion && tipo=="chaqueta"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                    arregloChaqueta.add(producto)

                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }
    fun readColeccionHoodie(tienda:String, adaptador: ropaAdapter, coleccionR: String){
        arregloHoodies.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()

                                if(coleccionR == coleccion && tipo == "hoodie"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                        arregloHoodies.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }

    fun readTipoHombre(tienda:String, adaptador: ropaAdapter, tipoR:String){
        arregloHombre.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()
                                if(tipoR == tipo && coleccion == "hombre"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                    arregloHombre.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }

    fun readTipoMujer(tienda:String, adaptador: ropaAdapter, tipoR:String){
        arregloMujer.clear()
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
                                var codigo = ropa.id
                                var descripcion = ropa.getString("descripcion").toString()
                                var precio = ropa.getDouble("precio").toString().toDouble()
                                var coleccion = ropa.getString("coleccion").toString()
                                var tipo = ropa.getString("tipo").toString()
                                val img = ropa.get("imagen").toString()
                                if(tipoR == tipo && coleccion=="mujer"){
                                    var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, img)
                                    arregloMujer.add(producto)
                                }

                            }
                            adaptador.notifyDataSetChanged()
                        }
                }
            }
    }

    fun getRopa(codigo: String) {
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")

        tiendaRef.whereEqualTo("nombre", "HubStyle")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val tiendaId = document.id
                    val ropaRef = db.collection("tienda").document(tiendaId).collection("productos")

                    // Ahora, puedes obtener el documento específico en la colección "productos"
                    val documentoProducto = ropaRef.document(codigo)

                    documentoProducto.get()
                        .addOnSuccessListener { productoSnapshot ->
                            // Aquí puedes trabajar con el snapshot del documento
                            if (productoSnapshot.exists()) {
                                // El documento existe, puedes acceder a sus datos
                                val descripcion = productoSnapshot.getString("descripcion").toString()
                                val precio = productoSnapshot.getDouble("precio").toString().toDouble()
                                val coleccion = productoSnapshot.getString("coleccion").toString()
                                val tipo = productoSnapshot.getString("tipo").toString()
                                val imagen = productoSnapshot.getString("imagen").toString()
                                var producto = Ropa(codigo, descripcion, precio, coleccion, tipo, imagen)
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Manejar errores si ocurrieron
                        }
                }
            }
    }

    fun getRopa(codigo: String, callback: RopaCallback) {
        val db = Firebase.firestore
        val tiendaRef = db.collection("tienda")

        tiendaRef.whereEqualTo("nombre", "HubStyle")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val tiendaId = document.id
                    val ropaRef = db.collection("tienda").document(tiendaId).collection("productos")

                    // Ahora, puedes obtener el documento específico en la colección "productos"
                    val documentoProducto = ropaRef.document(codigo)

                    documentoProducto.get()
                        .addOnSuccessListener { productoSnapshot ->
                            // Aquí puedes trabajar con el snapshot del documento
                            if (productoSnapshot.exists()) {
                                // El documento existe, puedes acceder a sus datos
                                val descripcion = productoSnapshot.getString("descripcion").toString()
                                val precio = productoSnapshot.getDouble("precio").toString().toDouble()
                                val coleccion = productoSnapshot.getString("coleccion").toString()
                                val tipo = productoSnapshot.getString("tipo").toString()
                                val imagen = productoSnapshot.getString("imagen").toString()
                                val producto = Ropa(codigo, descripcion, precio, coleccion, tipo, imagen)
                                callback.onRopaObtenida(producto) // Llama al callback con el producto
                            }
                        }
                        .addOnFailureListener { exception ->
                            callback.onError(exception) // Llama al callback en caso de error
                        }
                }
            }
    }


}