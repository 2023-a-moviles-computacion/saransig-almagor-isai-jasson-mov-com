package com.example.examen02_isaisaransig

import java.text.SimpleDateFormat
import java.util.*

class Producto {
    var idProducto: Int = 0
    var idTienda: Int = 0
    var descripcion: String = ""
    var fechaDeElaboracion: Long = 0
    var precio: Double = 0.0
    var descuento: Int = 0

    constructor(idProducto: Int,idTienda: Int, descripcion: String, fechaElab: Long, precio: Double, descuento: Int){
        this.idProducto = idProducto
        this.idTienda = idTienda
        this.descripcion = descripcion
        this.fechaDeElaboracion = fechaElab
        this.precio = precio
        this.descuento = descuento
    }

    constructor(){}

    fun longToString(longDate: Long): String{
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")
        val fechaString = formatoFecha.format(Date(longDate))
        return fechaString
    }

    override fun toString(): String {
        return """
            ID Tienda: ${idTienda}
            ID Producto: ${idProducto}
            Descripción: ${descripcion}
            Precio: ${precio}
            Descuento: ${descuento}
            Fecha de Elaboracion: ${longToString(fechaDeElaboracion)}
        """.trimIndent()
    }
}