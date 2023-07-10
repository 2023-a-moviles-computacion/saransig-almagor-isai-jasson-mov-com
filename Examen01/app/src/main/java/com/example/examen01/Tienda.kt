package com.example.examen01

class Tienda {
    var idTienda: Int = 0
    var nombre: String = ""
    var direccion: String = ""
    var ciudad: String = ""
    var numeroEmpleados: Int = 0

    constructor(idTienda: Int, nombre: String, direccion: String, ciudad: String, numeroEmpleados: Int){
        this.nombre = nombre
        this.direccion = direccion
        this.ciudad = ciudad
        this.numeroEmpleados = numeroEmpleados
    }

    constructor(){}

}