import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDate

class Universidad {
    var id_u: Int = 0
    var fechaCreacion: LocalDate? = null
    var nombre: String = ""
    var esPublica: Boolean = true
    var promedioNotas: Float = 0f
    var numeroEstudiantes: Int = 0

    var listaFacultades = mutableListOf<Facultad>()

    constructor(){

    }

    constructor(
        id_u: Int,
        fechaCreacion: LocalDate?,
        nombre: String,
        esPublica: Boolean,
        promedioNotas: Float,
        numeroEstudiantes: Int,
        listaFacultades: MutableList<Facultad>
    ) {
        this.id_u = id_u
        this.fechaCreacion = fechaCreacion
        this.nombre = nombre
        this.esPublica = esPublica
        this.promedioNotas = promedioNotas
        this.numeroEstudiantes = numeroEstudiantes
        this.listaFacultades = listaFacultades
    }

    fun obtenerAtributos(): String {
        var idsFacultades: String = ""
        listaFacultades.forEach{
            idsFacultades += ","+it.id_f.toString()
        }
        return "$id_u,$fechaCreacion,$nombre,$esPublica,$promedioNotas,$numeroEstudiantes,$idsFacultades"
    }
}