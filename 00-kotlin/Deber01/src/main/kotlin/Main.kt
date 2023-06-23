import java.io.*
import java.text.SimpleDateFormat
import java.util.*

fun main(){
    val archivoT = File("tiendas.csv")
    val crudTiendas = CRUDTienda(archivoT)
    val archivoP = File("productos.csv")
    val crudProductos = CRUDProductos(archivoP, 0)

    var flag = true
    var opcion: String
    while(flag){
        println("1. Tienda")
        println("2. Productos")
        println("0. Salir")
        opcion = readln()
        when(opcion) {
            "1" ->  menuTienda(crudTiendas)
            "2" ->  menuProducto(crudProductos)
            "0" ->  flag = false
            else -> println("Opción NO valida")
        }

    }
}

fun menuTienda(crudTiendas: CRUDTienda){
    var flag = true
    var opcion: String
    while(flag){
        println("1. Añadir Tienda")
        println("2. Mostrar Tiendas")
        println("3. Actualizar Tienda")
        println("4. Eliminar Tienda")
        println("0. Salir")
        opcion = readln()
        when(opcion){
            "1" -> añadirTienda(crudTiendas)
            "2" -> mostrarTiendas(crudTiendas)
            "3" -> actualizarTienda(crudTiendas)
            "4" -> eliminarTienda(crudTiendas)
            "0" -> flag = false
            else -> println("Opción NO valida")
        }
    }

}

fun menuProducto(crudProductos: CRUDProductos){
    var flag = true
    var opcion: String
    while(flag){
    println("1. Añadir Producto")
    println("2. Mostrar Productos")
    println("3. Actualizar Producto")
    println("4. Eliminar Producto")
    println("0. Salir")
    opcion = readln()
    when(opcion){
        "1" -> añadirProducto(crudProductos)
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
fun crearProducto():Producto{
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
    val producto = Producto(idProducto, descripcion, fecha, precio, vencido)
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


fun añadirProducto(crudProductos: CRUDProductos){
    val producto = crearProducto()
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

fun actualizarProducto(crudProductos: CRUDProductos){
    println("Ingrese el ID del producto que quiere modificar:")
    val idProducto = readLine()?.toIntOrNull() ?: 0
    val productoModificado = crearProducto()
    crudProductos.actualizarProducto(idProducto, productoModificado)
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