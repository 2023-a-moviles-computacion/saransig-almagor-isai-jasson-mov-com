import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CRUDProductos( val archivo: File, val idTienda: Int) {

    private var productos: MutableList<Producto> = mutableListOf()

    init {
        cargarProductos(idTienda)
    }

    fun crearProducto(producto: Producto) {
        productos.add(producto)
        guardarProductos()
        println("Se añadio un nuevo Producto")
    }

    fun mostrarProductos() {
        if (productos.isEmpty()) {
            println("No hay productos disponibles.")
        } else {
            productos.forEach { println(it)}
        }
    }

    fun productoExiste(id: Int):Producto?{
        val productoExistente = productos.find { it.idProducto == id }
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

    fun eliminarProducto(id: Int) {
        val productoExistente = productos.find { it.idProducto == id }
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
    private fun cargarProductos(id: Int) {
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
                if(producto[0].toInt() == id){
                    val idProducto = producto[1].toInt()
                    val descripcion = producto[2]
                    val fechaDeElaboracion = producto[3]
                    val fechaD:Date = convertToDate(fechaDeElaboracion)
                    val precio = producto[4].toDouble()
                    val descuento = producto[5].toBoolean()
                    productos.add(
                        Producto(id,idProducto, descripcion, fechaD, precio, descuento)
                    )
                }
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
