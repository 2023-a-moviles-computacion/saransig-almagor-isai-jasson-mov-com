import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CRUDProductos( val archivo: File) {

    private var productos: MutableList<Producto> = mutableListOf()

    init {
        cargarProductos()
    }

    fun crearProducto(producto: Producto) {
        productos.add(producto)
        guardarProductos()
        println("Se añadio un nuevo Producto")
    }

    fun mostrarProductos(id: Int) {
            productos.forEach {
                if(it.idTienda == id)
                println(it) }
    }

    fun productoExiste(idProducto: Int, idTienda: Int):Producto?{
        val productoExistente = productos.find { it.idProducto == idProducto && it.idTienda ==idTienda}
        return productoExistente
    }

    fun actualizarDescp(producto: Producto, newDesc: String){
        producto.descripcion = newDesc
        guardarProductos()
    }

    fun actualizarFec(producto: Producto, newFecha: Date){
        producto.fechaDeElaboracion = newFecha
        guardarProductos()
    }

    fun actualizarPrecio(producto: Producto, newPrecio:Double){
        producto.precio = newPrecio
        guardarProductos()
    }

    fun actualizarDescuento(producto: Producto, newDescuento: Boolean){
        producto.descuento = newDescuento
        guardarProductos()
    }

    fun eliminarProducto(productoExistente: Producto?) {
        if (productoExistente != null) {
            productos.remove(productoExistente)
            guardarProductos()
            println("El producto se elimino exitosamente.")
        } else {
            println("No se encontró ningún producto con el ID proporcionado.")
        }
    }

    fun convertToDate(fechaStr: String):Date{
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
        val fecha = dateFormat.parse(fechaStr)
        return fecha
    }
    fun cargarProductos() {
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
                    val idTienda = producto[0].toInt()
                    val idProducto = producto[1].toInt()
                    val descripcion = producto[2]
                    val fechaDeElaboracion = producto[3]
                    val fechaD:Date = convertToDate(fechaDeElaboracion)
                    val precio = producto[4].toDouble()
                    val descuento = producto[5].toBoolean()
                    productos.add(
                        Producto(idTienda,idProducto, descripcion, fechaD, precio, descuento)
                    )
            }
        }
    }


    private fun guardarProductos() {
        val Fwriter = FileWriter(archivo)
        val Pwriter = PrintWriter(Fwriter)

        productos.forEach { producto ->
            val productoCsv = "${producto.idTienda};${producto.idProducto};${producto.descripcion};${producto.fechaDeElaboracion};${producto.precio};${producto.descuento}"
            Pwriter.println(productoCsv)
        }
        Pwriter.close()
    }

}
