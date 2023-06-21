import java.io.File
import java.nio.file.Files

data class Tienda(
    val idTienda: Int,
    val nombre: String,
    val direccion: String,
    val ciudad: String,
    val numeroEmpleados: Int
)

class TiendaManager(private val archivo: File) {
    private var tiendas: MutableList<Tienda> = mutableListOf()

    init {
        if (archivo.exists()) {
            cargarTiendasDesdeArchivo()
        } else {
            archivo.createNewFile()
        }
    }

    fun crearTienda(tienda: Tienda) {
        tiendas.add(tienda)
        guardarTiendasEnArchivo()
    }

    fun obtenerTiendaPorId(idTienda: Int): Tienda? {
        return tiendas.find { it.idTienda == idTienda }
    }

    fun actualizarTienda(tiendaActualizada: Tienda) {
        val index = tiendas.indexOfFirst { it.idTienda == tiendaActualizada.idTienda }
        if (index != -1) {
            tiendas[index] = tiendaActualizada
            guardarTiendasEnArchivo()
        }
    }

    fun eliminarTienda(idTienda: Int) {
        tiendas.removeIf { it.idTienda == idTienda }
        guardarTiendasEnArchivo()
    }

    private fun cargarTiendasDesdeArchivo() {
        val contenidoArchivo = String(Files.readAllBytes(archivo.toPath()))
        tiendas = parsearTiendas(contenidoArchivo)
    }

    private fun guardarTiendasEnArchivo() {
        val contenidoArchivo = generarContenidoArchivo(tiendas)
        archivo.writeText(contenidoArchivo)
    }

    private fun parsearTiendas(contenidoArchivo: String): MutableList<Tienda> {
        val lineas = contenidoArchivo.lines()
        val tiendas = mutableListOf<Tienda>()

        for (linea in lineas) {
            val campos = linea.split(",").map { it.trim() }
            if (campos.size == 5) {
                val idTienda = campos[0].toIntOrNull()
                val nombre = campos[1]
                val direccion = campos[2]
                val ciudad = campos[3]
                val numeroEmpleados = campos[4].toIntOrNull()

                if (idTienda != null && numeroEmpleados != null) {
                    val tienda = Tienda(idTienda, nombre, direccion, ciudad, numeroEmpleados)
                    tiendas.add(tienda)
                }
            }
        }

        return tiendas
    }

    private fun generarContenidoArchivo(tiendas: List<Tienda>): String {
        val contenido = StringBuilder()
        for (tienda in tiendas) {
            val linea = "${tienda.idTienda}, ${tienda.nombre}, ${tienda.direccion}, ${tienda.ciudad}, ${tienda.numeroEmpleados}"
            contenido.appendLine(linea)
        }
        return contenido.toString()
    }
}

fun main() {
    val archivo = File("tiendas.txt")
    val tiendaManager = TiendaManager(archivo)

    // Ejemplo de uso: crear una nueva tienda
    val nuevaTienda = Tienda(1, "Mi Tienda", "Calle Principal 123", "Ciudad Ejemplo", 10)
    tiendaManager.crearTienda(nuevaTienda)

    // Ejemplo de uso: obtener una tienda por su ID
    val tiendaObtenida = tiendaManager.obtenerTiendaPorId(1)
    println("Información de la tienda obtenida:")
    println(tiendaObtenida)

    // Ejemplo de uso: actualizar una tienda existente
    val tiendaExistente = tiendaManager.obtenerTiendaPorId(1)
    if (tiendaExistente != null) {
        val tiendaActualizada = tiendaExistente.copy(nombre = "Mi Tienda Actualizada")
        tiendaManager.actualizarTienda(tiendaActualizada)
    }


}
