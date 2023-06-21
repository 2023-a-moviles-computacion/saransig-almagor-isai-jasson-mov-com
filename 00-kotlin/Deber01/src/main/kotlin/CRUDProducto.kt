import java.io.*
import java.util.*

class CRUDProductos( val archivo: File) {

    private var productos: MutableList<Producto> = mutableListOf()

    init {
        cargarProductos()
    }

    fun crearProducto(producto: Producto) {
        productos.add(producto)
        guardarProductos()
        println("El producto ha sido creado exitosamente.")
    }

    fun mostrarProductos() {
        if (productos.isEmpty()) {
            println("No hay productos disponibles.")
        } else {
            println("Lista de productos:")
            productos.forEach { println(it) }
        }
    }

    fun actualizarProducto(id: Int, productoActualizado: Producto) {
        val productoExistente = productos.find { it.idProducto == id }
        if (productoExistente != null) {
            productos.remove(productoExistente)
            productos.add(productoActualizado)
            guardarProductos()
            println("El producto ha sido actualizado exitosamente.")
        } else {
            println("No se encontró ningún producto con el ID proporcionado.")
        }
    }

    fun eliminarProducto(id: Int) {
        val productoExistente = productos.find { it.idProducto == id }
        if (productoExistente != null) {
            productos.remove(productoExistente)
            guardarProductos()
            println("El producto ha sido eliminado exitosamente.")
        } else {
            println("No se encontró ningún producto con el ID proporcionado.")
        }
    }
    private fun cargarProductos() {
        if (archivo.exists()) {
            val reader = FileReader(archivo)
            val scanner = Scanner(reader)
            val productosArray = mutableListOf<String>()

            while (scanner.hasNextLine()) {
                val linea = scanner.nextLine()
                productosArray.add(linea)
            }

            scanner.close()
            reader.close()

            productosArray.forEach { productoString ->
                val producto = productoString.split(";")
                val idProducto = producto[0].toInt()
                val descripcion = producto[1]
                val fechaDeElaboracion = producto[2]
                val precio = producto[3].toDouble()
                val descuento = producto[4].toBoolean()

                productos.add(
                    Producto(idProducto, descripcion, fechaDeElaboracion, precio, descuento)
                )
            }

            println("Se han cargado ${productos.size} productos desde el archivo.")
        } else {
            println("El archivo no existe. No se cargaron productos.")
        }
    }


    private fun guardarProductos() {
        val Fwriter = FileWriter(archivo)
        val Pwriter = PrintWriter(Fwriter)

        productos.forEach { producto ->
            val productoJson = "${producto.idProducto};${producto.descripcion};${producto.fechaDeElaboracion};${producto.precio};${producto.descuento}"
            Pwriter.println(productoJson)
        }

        Pwriter.close()

        println("Los productos se han guardado en el archivo exitosamente.")
    }

}
