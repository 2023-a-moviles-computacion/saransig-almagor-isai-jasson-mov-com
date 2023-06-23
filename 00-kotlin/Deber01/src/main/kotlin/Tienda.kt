class Tienda (
    var idTienda: Int,
    var nombre: String,
    var direccion: String,
    var ciudad: String,
    var numeroEmpleados: Int,
    var productos: MutableList<Producto>

) {
    override fun toString():String {
        return " ----------- INFORMACIÓN DE LA TIENDA -----------\n" +
                "Id de la tienda: $idTienda\n" +
                "Nombre: $nombre\n" +
                "Dirección: $direccion\n" +
                "Ciudad: $ciudad\n" +
                "Número de empleados: $numeroEmpleados"

    }

}