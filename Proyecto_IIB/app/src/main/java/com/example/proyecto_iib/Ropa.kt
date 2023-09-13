package com.example.proyecto_iib

class Ropa {
    var codigo: String? = ""
    var descripcion: String? = ""
    var precio: Double? = 0.0
    var coleccion: String? = ""
    var tipo:String? = ""

    constructor(descripcion:String?, precio:Double?, coleccion:String?, tipo:String?){
        this.descripcion = descripcion
        this.precio = precio
        this.coleccion = coleccion
        this.tipo = tipo
    }

    constructor(){}

}