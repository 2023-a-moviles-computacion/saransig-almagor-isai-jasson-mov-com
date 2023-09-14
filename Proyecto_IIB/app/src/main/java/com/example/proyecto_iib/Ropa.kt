package com.example.proyecto_iib

class Ropa {
    var codigo: String? = ""
    var descripcion: String? = ""
    var precio: Double? = 0.0
    var coleccion: String? = ""
    var talla: String? = ""
    var colorRopa: String? = ""
    var detalle: String? = ""
    var tipo:String? = ""
    var imagen: String? = ""

    constructor(descripcion:String?, precio:Double?, coleccion:String?, tipo:String?, talla:String?, colorRopa: String?, detalle:String?, imagen:String?){
        this.descripcion = descripcion
        this.precio = precio
        this.coleccion = coleccion
        this.tipo = tipo
        this.talla = talla
        this.colorRopa = colorRopa
        this.detalle = detalle
        this.imagen = imagen
    }

    constructor(descripcion:String?, precio:Double?, coleccion:String, tipo:String){

    }

    constructor(){}

}