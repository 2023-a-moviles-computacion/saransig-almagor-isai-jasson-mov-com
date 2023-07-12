import java.text.SimpleDateFormat
import java.util.*

class Producto(
    val idTienda: Int,
    var idProducto: Int,
    var descripcion: String,
    var fechaDeElaboracion: Date,
    var precio: Double,
    var descuento: Boolean
) {


    override fun toString(): String {
        return "----------- INFORMACIÓN DEL PRODUCTO -----------\n" +
                "ID del producto: $idProducto\n" +
                "Descripción: $descripcion\n" +
                "Fecha de elaboración: ${fechaDeElaboracion}\n" +
                "Precio: $precio\n" +
                "¿Descuento?: $descuento" + ""
    }



}



