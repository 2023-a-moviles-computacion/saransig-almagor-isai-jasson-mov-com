package com.example.proyecto_iib

class Carrito {
    var cliente: Cliente? = null
    var ropa: Ropa? = null
    var cantidad: Int? = 0

    constructor(cliente: Cliente?, ropa: Ropa?, cantidad: Int?){
        this.cliente = cliente
        this.ropa = ropa
        this.cantidad = cantidad
    }

    constructor(){}
}