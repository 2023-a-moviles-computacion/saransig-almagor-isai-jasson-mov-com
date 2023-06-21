import java.util.Date
import java.io.*
fun main(){
    var flag = true
    var opcion: String

    menuProducto()

}

fun menuTienda(){
    val archivoT = File("tiendas.json")
    val crudTiendas = CRUDTienda(archivoT)

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
            "2" -> mostrarTienda(crudTiendas)
            "3" -> actualizarTienda(crudTiendas)
            "4" -> eliminarTienda(crudTiendas)
            "0" -> flag = false
            else -> println("Opción NO valida")
        }
    }

}

fun menuProducto(){

    val archivoP = File("productos.json")
    val crudProductos = CRUDProductos(archivoP)


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

fun crearProducto():Producto{
    println("Ingrese el ID del producto:")
    val idProducto = readLine()?.toIntOrNull() ?: 0

    println("Ingrese la descripción del producto:")
    val descripcion = readLine() ?: ""

    println("Ingrese la fecha de elaboración del producto (formato yyyy-MM-dd):")
    val fechaDeElaboracion = readLine() ?: ""

    println("Ingrese el precio del producto:")
    val precio = readLine()?.toDoubleOrNull() ?: 0.0

    println("¿El producto tiene descuento? (true/false):")
    val vencido = readLine()?.toBoolean() ?: false
    val producto = Producto(idProducto, descripcion, fechaDeElaboracion, precio, vencido)
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
    crudTiendas.crearTiend(tienda)
}

fun mostrarTienda(crudTiendas:CRUDTienda){
    crudTiendas.mostrarTiendas()
}

fun actualizarTienda(crudTiendas:CRUDTienda){

}

fun eliminarTienda(crudTiendas:CRUDTienda){

}