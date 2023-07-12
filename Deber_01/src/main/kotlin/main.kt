import java.io.*
import java.text.SimpleDateFormat
import java.util.*

fun main(){

    var archivoT = File("tiendas.csv")
    var crudTiendas = CRUDTienda(archivoT)
    var archivoP = File("productos.csv")
    var crudProductos:CRUDProductos

    var idTienda: Int?

    var flag = true
    var opcion: String
    while(flag){
        println("------------------------------------")
        println("Menú de TIENDAS")
        println("1. Añadir Tienda")
        println("2. Mostrar Tiendas")
        println("3. Actualizar Tienda")
        println("4. Eliminar Tienda")
        println("5. Productos")
        println("0. Salir")
        opcion = readln()
        when(opcion){
            "1" -> añadirTienda(crudTiendas)
            "2" -> mostrarTiendas(crudTiendas)
            "3" -> actualizarTienda(crudTiendas)
            "4" -> eliminarTienda(crudTiendas)
            "5" ->{
                println("Ingrese el ID de la tienda para los productos")
                idTienda = readLine()?.toIntOrNull()?:0
                crudProductos = CRUDProductos(archivoP, idTienda)
                menuProducto(crudProductos, idTienda)
            }
            "0" -> {flag = false
                crudTiendas.guardarTiendas()
            }
            else -> println("Opción NO valida")
        }
    }

}


fun menuProducto(crudProductos: CRUDProductos, idTienda: Int){
    var flag = true
    var opcion: String
    while(flag){
        println("------------------------------------")
        println("Menú de PRODUCTOS")
        println("1. Añadir Producto")
        println("2. Mostrar Productos")
        println("3. Actualizar Producto")
        println("4. Eliminar Producto")
        println("0. Salir")
        opcion = readln()
        when(opcion){
            "1" -> añadirProducto(crudProductos, idTienda)
            "2" -> mostrarProductos(crudProductos)
            "3" -> actualizarProducto(crudProductos)
            "4" -> eliminarProducto(crudProductos)
            "0" -> flag = false
            else -> println("Opción NO valida")
        }
    }
}

fun convertToDate(fechaStr: String):Date{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val fecha = dateFormat.parse(fechaStr)
    return fecha
}
fun crearProducto(idTienda: Int):Producto{
    println("Ingrese el ID del producto:")
    val idProducto = readLine()?.toIntOrNull() ?: 0

    println("Ingrese la descripción del producto:")
    val descripcion = readLine() ?: ""

    println("Ingrese la fecha de elaboración del producto (formato yyyy-MM-dd):")
    val fechaStr = readLine()?: ""
    val fecha: Date = convertToDate(fechaStr)

    println("Ingrese el precio del producto:")
    val precio = readLine()?.toDoubleOrNull() ?: 0.0

    println("¿El producto tiene descuento? (true/false):")
    val vencido = readLine()?.toBoolean() ?: false
    val producto = Producto(idTienda, idProducto, descripcion, fecha, precio, vencido)
    return producto
}

fun crearTienda():Tienda{
    println("Ingrese el ID de la Tienda:")
    val idTienda = readLine()?.toIntOrNull() ?: 0

    println("Ingrese el nombre de la tienda:")
    val nombre = readLine() ?: ""

    println("Ingrese la dirección de la tienda:")
    val direccion = readLine() ?: ""

    println("Ingrese la ciudad de la tienda:")
    val ciudad = readLine()?: ""

    println("Ingrese el numero de Empleados:")
    val numeroEmpleados = readLine()?.toIntOrNull() ?: 0

    val tienda = Tienda(idTienda, nombre, direccion, ciudad, numeroEmpleados)
    return tienda
}


fun añadirProducto(crudProductos: CRUDProductos, idTienda: Int){
    val producto = crearProducto(idTienda)
    crudProductos.crearProducto(producto)
}

fun eliminarProducto(crudProductos: CRUDProductos){
    println("Ingrese el ID del producto que quiere eliminar:")
    val idProducto = readLine()?.toIntOrNull() ?: 0

    crudProductos.eliminarProducto(idProducto)
}

fun mostrarProductos(crudProductos: CRUDProductos){
    crudProductos.mostrarProductos()
}

fun actualizarProducto(crudProductos: CRUDProductos) {
    println("Ingrese el ID del Producto que quiere modificar")
    var idProducto = readln().toInt()
    var producto: Producto? = crudProductos.productoExiste(idProducto)
    if (producto != null) {
        println("------------------------------------")
        println("Menú actualización PRODUCTOS")
        println("1. Actualizar Descripcion")
        println("2. Actualizar Fecha de Elaboracion")
        println("3. Actualizar Precio")
        println("4. Actualizar Descuento")
        var opc = readln()
        var newItem: String
        when (opc) {
            "1" -> {
                println("Ingrese la nueva descripcion del Producto")
                newItem = readln()
                crudProductos.actualizarDescp(producto, newItem)
            }
            "2" -> {
                println("Ingrese la nueva Fecha de Elaboracion")
                newItem = readln()
                crudProductos.actualizarFec(producto, convertToDate(newItem))

            }
            "3" -> {
                println("Ingrese el nuevo Precio")
                newItem = readln()
                crudProductos.actualizarPrecio(producto, newItem.toDouble())
            }
            "4" -> {
                println("Ingrese el nuevo Descuento")
                newItem = readln()
                crudProductos.actualizarDescuento(producto, newItem.toBoolean())
            }
            else -> println("Opción NO valida")


        }

    }

}


fun añadirTienda(crudTiendas:CRUDTienda){
    val tienda = crearTienda()
    crudTiendas.crearTienda(tienda)
}

fun mostrarTiendas(crudTiendas:CRUDTienda){
    crudTiendas.mostrarTiendas()
}

fun actualizarTienda(crudTiendas:CRUDTienda){
    println("Ingrese el ID de la Tienda que quiere modificar")
    var idTienda = readln()?.toInt()
    var tienda:Tienda? = crudTiendas.tiendaExiste(idTienda)
    if(tienda!=null){
        println("------------------------------------")
        println("Menú actualización TIENDAS")
        println("1. Actualizar Nombre")
        println("2. Actualizar Direccion")
        println("3. Actualizar Ciudad")
        println("4. Actualizar Numero de Empleados")
        var opc = readln()
        var newItem:String
        when(opc){
            "1" -> {
                println("Ingrese el nuevo nombre de la Tienda")
                newItem = readln()
                crudTiendas.actualizarNombre(tienda,newItem)
            }
            "2" -> {
                println("Ingrese el nuevo direccion de la Tienda")
                newItem = readln()
                crudTiendas.actualizarDir(tienda,newItem)
            }
            "3" -> {
                println("Ingrese el nuevo ciudad de la Tienda")
                newItem = readln()
                crudTiendas.actualizarCiudad(tienda,newItem)
            }
            "4" -> {
                println("Ingrese el nuevo Numero de Empleados de la Tienda")
                newItem = readln()
                crudTiendas.actualizarNumEmp(tienda,newItem.toInt())
            }
            else -> println("Opción NO valida")

        }

    }


}

fun eliminarTienda(crudTiendas:CRUDTienda){
    println("Ingrese el ID de la Tienda que quiere eliminar:")
    val idTienda = readLine()?.toIntOrNull() ?: 0
    val tienda: Tienda? = crudTiendas.tiendaExiste(idTienda)
    crudTiendas.eliminarTienda(tienda)
}
