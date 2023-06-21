import java.util.*
import java.time.LocalDate

class Facultad {
    var id_f: Int = 0
    var fechaCreacion: LocalDate? = null
    var nombre: String = ""
    var numeroProfesores: Int = 0
    var poseeAsociacionDeEstudiantes: Boolean = true
    var promedioInvestigativo: Float = 0f

    constructor(){

    }

    constructor(
        id_f: Int,
        fechaCreacion: LocalDate?,
        nombre: String,
        numeroProfesores: Int,
        poseeAsociacionDeEstudiantes: Boolean,
        promedioInvestigativo: Float
    ) {
        this.id_f = id_f
        this.fechaCreacion = fechaCreacion
        this.nombre = nombre
        this.numeroProfesores = numeroProfesores
        this.poseeAsociacionDeEstudiantes = poseeAsociacionDeEstudiantes
        this.promedioInvestigativo = promedioInvestigativo
    }

    override fun toString(): String {
        return "Id: $id_f, Nombre: $nombre, " + "\n" +
                "Fecha de creaciòn: $fechaCreacion, " + "\n" +
                "Número de profesores: $numeroProfesores, " + "\n" +
                "Posee asociaciòn de estudiantes: $poseeAsociacionDeEstudiantes, " + "\n" +
                "Promedio de Investigación: $promedioInvestigativo"
    }

    fun obtenerAtributos(): String {
        return "$id_f,$fechaCreacion,$nombre,$numeroProfesores,$poseeAsociacionDeEstudiantes, $promedioInvestigativo"
    }
}
