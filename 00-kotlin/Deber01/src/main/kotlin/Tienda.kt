class Tienda (
    val idTienda: Int,
    val nombre: String,
    val direccion: String,
    val ciudad: String,
    val numeroEmpleados: Int
) {
    override fun toString():String {
        return "  INFORMACIÓN DE LA TIENDA \n" +
                "Id de la tienda: $idTienda\n" +
                "Nombre: $nombre\n" +
                "Dirección: $direccion\n" +
                "Ciudad: $ciudad\n" +
                "Número de empleados: $numeroEmpleados"
    }

}