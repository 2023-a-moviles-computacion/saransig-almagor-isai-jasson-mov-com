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
    var puntaje: Int? = 0

    constructor(descripcion:String?, precio:Double?, coleccion:String?, tipo:String?, talla:String?, colorRopa: String?, detalle:String?, imagen:String?, puntaje: Int?){
        this.descripcion = descripcion
        this.precio = precio
        this.coleccion = coleccion
        this.tipo = tipo
        this.talla = talla
        this.colorRopa = colorRopa
        this.detalle = detalle
        this.imagen = imagen
        this.puntaje = puntaje
    }

    constructor(descripcion:String?, precio:Double?, coleccion:String, tipo:String, imagen:String?){
        this.descripcion = descripcion
        this.precio = precio
        this.coleccion = coleccion
        this.tipo = tipo
        this.imagen = imagen
    }

    constructor(){}

    override fun toString(): String {
        return "Producto: $descripcion\n" +
                "Precio: $precio\n" +
                "Colección: $coleccion\n" +
                "Tipo: $tipo\n" +
                "Talla: $talla\n" +
                "Color de Ropa: $colorRopa\n" +
                "Detalle: $detalle\n" +
                "Imagen: $imagen\n" +
                "Puntaje: $puntaje"
    }

}