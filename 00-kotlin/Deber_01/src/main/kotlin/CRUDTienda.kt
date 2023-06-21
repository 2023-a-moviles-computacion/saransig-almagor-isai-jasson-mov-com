import java.io.*
import java.util.*

class CRUDTienda( val archivo: File) {

    private var tiendas: MutableList<Tienda> = mutableListOf()

    init {
        cargarTiendas()
    }

    fun crearTiend(tienda: Tienda) {
        tiendas.add(tienda)
        guardarProductos()
        println("La tienda ha sido creado exitosamente.")
    }

    fun mostrarTiendas() {
        if (tiendas.isEmpty()) {
            println("No hay productos disponibles.")
        } else {
            println("Lista de productos:")
            tiendas.forEach { println(it) }
        }
    }

    fun actualizarTienda(id: Int, productoActualizado: Tienda) {
        val productoExistente = tiendas.find { it.idTienda == id }
        if (productoExistente != null) {
            tiendas.remove(productoExistente)
            tiendas.add(productoActualizado)
            guardarProductos()
            println("La tienda ha sido actualizado exitosamente.")
        } else {
            println("No se encontró ninguna tienda con el ID proporcionado.")
        }
    }

    fun eliminarTienda(id: Int) {
        val tiendaExistente = tiendas.find { it.idTienda== id }
        if (tiendaExistente != null) {
            tiendas.remove(tiendaExistente)
            guardarProductos()
            println("El producto ha sido eliminado exitosamente.")
        } else {
            println("No se encontró ningún producto con el ID proporcionado.")
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

            println("Se han cargado ${tiendas.size} productos desde el archivo.")
        } else {
            println("El archivo no existe. No se cargaron productos.")
        }
    }


    private fun guardarProductos() {
        val writer = FileWriter(archivo)
        val jsonWriter = PrintWriter(writer)

        tiendas.forEach { tienda ->
            val jsonString = "${tienda.idTienda};${tienda.nombre};${tienda.direccion};${tienda.ciudad};${tienda.numeroEmpleados}"
            jsonWriter.println(jsonString)
        }

        jsonWriter.close()

        println("Los productos se han guardado en el archivo exitosamente.")
    }

}
