package com.example.proyecto_iib

class Pedido {
    var list_pedidos = arrayListOf<Carrito>()
    var fecha: Long? = 0
    constructor(list_pedidos: ArrayList<Carrito>, fecha:Long?){
        this.list_pedidos = list_pedidos
        this.fecha = fecha
    }
    constructor(){}
}