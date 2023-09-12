package com.example.deber03

import java.util.*

class BDEstados {
    companion object{
        var arregloEstados = arrayListOf<Estados>()
        init {
            arregloEstados
                .add(
                    Estados("Edison", "hace 3 minutos", true, "estado1")
                )
            arregloEstados
                .add(
                    Estados("Maria", "hace 30 minutos", true, "estado2")
                )
            arregloEstados
                .add(
                    Estados("Naty", "Hoy, 13:00", true, "estado3")
                )
            arregloEstados
                .add(
                    Estados("Luis", "Hoy, 12:00", true, "estado4")
                )
            arregloEstados
                .add(
                    Estados("Liseth", "Hoy, 10:00", true, "estado5")
                )

        }
    }
}