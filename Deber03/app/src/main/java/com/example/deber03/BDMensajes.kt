package com.example.deber03
import java.util.Date

class BDMensajes {
    companion object{
        var arregloMensajes = arrayListOf<Mensaje>()
        init {
            arregloMensajes
                .add(
                    Mensaje("Isai", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "12:00", true, "persona1")
                )
            arregloMensajes
                .add(
                    Mensaje("Edison", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "11:00", false, "persona2")
                )
            arregloMensajes
                .add(
                    Mensaje("Jose", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "18:00", true, "persona3")
                )
            arregloMensajes
                .add(
                    Mensaje("Maria", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "02:00", false, "persona4")
                )
            arregloMensajes
                .add(
                    Mensaje("Evelyn", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "10:00", false, "persona5")
                )
            arregloMensajes
                .add(
                    Mensaje("Naty", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "17:00", true, "persona6")
                )
            arregloMensajes
                .add(
                    Mensaje("Karla", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "19:00", true, "persona7")
                )
            arregloMensajes
                .add(
                    Mensaje("Luis", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "21:00", false, "persona8")
                )
            arregloMensajes
                .add(
                    Mensaje("Liseth", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", Date(), "22:00", false, "persona9")
                )
        }
    }
}