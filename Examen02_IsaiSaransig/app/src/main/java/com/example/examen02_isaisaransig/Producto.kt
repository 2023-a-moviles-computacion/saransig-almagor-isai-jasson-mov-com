package com.example.examen02_isaisaransig

import java.text.SimpleDateFormat
import java.util.*

class Producto {

    var descripcion: String? = ""
    var fechaDeElaboracion: Long? = 0
    var precio: Double? = 0.0
    var descuento: Int? = 0

    constructor(descripcion: String?, fechaElab: Long?, precio: Double?, descuento: Int?){
        this.descripcion = descripcion
        this.fechaDeElaboracion = fechaElab
        this.precio = precio
        this.descuento = descuento
    }


    constructor(){}

    fun longToString(longDate: Long?): String{
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd")
        val fechaString = formatoFecha.format(Date(longDate?:0))
        return fechaString
    }

    override fun toString(): String {
        return """
            Descripción: ${descripcion}
            Precio: ${precio}
            Fecha: ${longToString(fechaDeElaboracion)}
            Descuento: ${descuento}            
        """.trimIndent()
    }
}