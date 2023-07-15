import java.io.*
import java.util.*

class CRUDTienda( val archivo: File) {

    private var tiendas: MutableList<Tienda> = mutableListOf()

    init {
        cargarTiendas()
    }

    fun crearTienda(tienda: Tienda) {
        tiendas.add(tienda)
        guardarTiendas()
        println("Se añadio una nueva tienda")
    }

    fun mostrarTiendas() {
        if (tiendas.isEmpty()) {
            println("No se tiene tiendas almacenadas")
        } else {
            tiendas.forEach { println(it) }
        }
    }

    fun tiendaExiste(id: Int): Tienda?{
        var tienda = tiendas.find { it.idTienda == id }
        return tienda
    }

    fun actualizarNombre(tienda:Tienda, newNombre: String){
        tienda.nombre = newNombre
        guardarTiendas()
    }

    fun actualizarDir(tienda:Tienda, newDir: String){
        tienda.direccion = newDir
        guardarTiendas()
    }

    fun actualizarCiudad(tienda:Tienda, newCiudad: String){
        tienda.ciudad = newCiudad
        guardarTiendas()
    }
    fun actualizarNumEmp(tienda:Tienda, newNumEmp: Int){
        tienda.numeroEmpleados = newNumEmp
        guardarTiendas()
    }


    fun eliminarTienda(tienda: Tienda?) {
        if (tienda != null) {
            tiendas.remove(tienda)
            guardarTiendas()
            println("Tienda eliminada")
        } else {
            println("No se encontro ninguna tienda con ID proporcionado.")
        }
    }
    private fun cargarTiendas() {
        if (archivo.exists()) {
            val reader = FileReader(archivo)
            val scanner = Scanner(reader)
            val tiendasArray = mutableListOf<String>()

            while (scanner.hasNextLine()) {
                val linea = scanner.nextLine()
                tiendasArray.add(linea)
            }

            scanner.close()
            reader.close()

            tiendasArray.forEach { tiendaString ->
                val tienda = tiendaString.split(";")
                val idTienda = tienda[0].toInt()
                val nombre = tienda[1]
                val direccion = tienda[2]
                val ciudad = tienda[3]
                val numeroEmpleados = tienda[4].toInt()

                tiendas.add(
                    Tienda(idTienda, nombre, direccion, ciudad, numeroEmpleados)
                )
            }
        }
    }


    private fun guardarTiendas() {
        val Fwriter = FileWriter(archivo)
        val PWriter = PrintWriter(Fwriter)

        tiendas.forEach { tienda ->
            val tiendaCsv = "${tienda.idTienda};${tienda.nombre};${tienda.direccion};${tienda.ciudad};${tienda.numeroEmpleados}"
            PWriter.println(tiendaCsv)
        }
        PWriter.close()
    }

}
