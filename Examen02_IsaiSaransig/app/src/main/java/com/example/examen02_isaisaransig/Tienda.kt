package com.example.examen02_isaisaransig

class Tienda {
    var nombre: String? = ""
    var direccion: String? = ""
    var ciudad: String? = ""
    var numeroEmpleados: Int? = 0

    constructor(){}
    constructor(nombre: String?, direccion:String?, ciudad: String?, numeroEmpleados: Int?){
        this.nombre = nombre
        this.direccion = direccion
        this.ciudad = ciudad
        this.numeroEmpleados = numeroEmpleados
    }

    override fun toString(): String {
        return """
            Nombre: ${nombre}
            Dirección: ${direccion}
            Ciudad: ${ciudad}
            Número de Empleados: ${numeroEmpleados}
        """.trimIndent()
    }
}