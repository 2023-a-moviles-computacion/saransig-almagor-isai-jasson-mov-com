package com.example.deber03
import java.util.Date

class BDMensajes {
    companion object{
        var arregloMensajes = arrayListOf<Mensaje>()
        init {
            arregloMensajes
                .add(
                    Mensaje("Isai", "jajajaj", Date(), "12:00", true, "persona1")
                )
            arregloMensajes
                .add(
                    Mensaje("Edison", "Confirma para cuando es el deber", Date(), "11:00", false, "persona2")
                )
            arregloMensajes
                .add(
                    Mensaje("Jose", "Si era para hoy .-.", Date(), "18:00", true, "persona3")
                )
            arregloMensajes
                .add(
                    Mensaje("Maria", "jajaj si", Date(), "02:00", false, "persona4")
                )
            arregloMensajes
                .add(
                    Mensaje("Evelyn", "ya encontraste?", Date(), "10:00", false, "persona5")
                )
            arregloMensajes
                .add(
                    Mensaje("Naty", "cuanto sacaste?", Date(), "17:00", true, "persona6")
                )
            arregloMensajes
                .add(
                    Mensaje("Karla", "No :(", Date(), "19:00", true, "persona7")
                )
            arregloMensajes
                .add(
                    Mensaje("Luis", "un counter?", Date(), "21:00", false, "persona8")
                )
            arregloMensajes
                .add(
                    Mensaje("Liseth", "Ok", Date(), "22:00", false, "persona9")
                )
        }
    }
}