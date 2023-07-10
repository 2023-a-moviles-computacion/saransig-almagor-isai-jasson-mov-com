package com.example.examen01

import java.util.*

class Producto {
    var idProducto: Int = 0
    var idTienda: Int = 0
    var descripcion: String = ""
    var fechaDeElaboracion: Date? = null
    var precio: Double = 0.0
    var descuento: Boolean = false

    constructor(idProducto: Int,idTienda: Int, descripcion: String, fechaElab: Date, precio: Double, descuento: Boolean){
        this.idProducto = idProducto
        this.idTienda = idTienda
        this.descripcion = descripcion
        this.fechaDeElaboracion = fechaElab
        this.precio = precio
        this.descuento = descuento
    }

    constructor(){}
}