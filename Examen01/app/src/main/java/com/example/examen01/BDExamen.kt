package com.example.examen01
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

var DBName = "Examen"
val tableT = "Tienda"
val tableP = "Producto"

class BDExamen(context: Context): SQLiteOpenHelper(context, DBName, null, 1) {

    companion object {
        var arregloTiendas = ArrayList<Tienda>()
        var arregloProductos = ArrayList<Producto>()
    }
    override fun onCreate(db: SQLiteDatabase?) {
        var script = """
            CREATE TABLE TIENDA (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
             nombre VARCHAR(50),
             direccion VARCHAR(100),
             ciudad VARCHAR(50),
             numEmpleados INTEGER
            )
        """.trimIndent()

    db?.execSQL(script)

        script = """
            CREATE TABLE PRODUCTO (
            id INTEGER PRIMARY KEY AUTOINCREMENT, 
            idTienda INTEGER,
            descripcion VARCHAR(100),
            fechaDeElaboracion DATE,
            precio DOUBLE,
            descuento INTEGER,
            FOREIGN KEY(idTienda) REFERENCES TIENDA(id)
            )
        """.trimIndent()
    db?.execSQL(script)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertTienda(nombre: String?, direccion: String?, ciudad: String?, numEmpleados: Int?): Boolean{
        val db = writableDatabase
        val content = ContentValues()
        content.put("nombre", nombre)
        content.put("direccion", direccion)
        content.put("ciudad", ciudad)
        content.put("numEmpleados", numEmpleados)
        val resultado = db.insert(tableT, null, content)
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun readTiendas() {
        BDExamen.arregloTiendas.clear() // Limpiar la lista existente

        val db = readableDatabase
        val script = "SELECT * FROM ${tableT} "
        val resultado = db.rawQuery(script, null)

        if (resultado.moveToFirst()) {
            do {
                val tienda = Tienda()
                tienda.idTienda = resultado.getInt(0)
                tienda.nombre = resultado.getString(1)
                tienda.direccion = resultado.getString(2)
                tienda.ciudad = resultado.getString(3)
                tienda.numeroEmpleados = resultado.getInt(4)
                BDExamen.arregloTiendas.add(tienda)
            } while (resultado.moveToNext())
        }
    }

    fun readTiendaByID(id: Int): Tienda{
        val db = readableDatabase
        val script = "SELECT * FROM ${tableT} WHERE id = ?"

        val resultado = db.rawQuery(script, arrayOf(id.toString()))
        var tienda = Tienda()
        if (resultado.moveToFirst()){
            do {
                tienda.idTienda = resultado.getInt(0)
                tienda.nombre = resultado.getString(1)
                tienda.direccion = resultado.getString(2)
                tienda.ciudad = resultado.getString(3)
                tienda.numeroEmpleados = resultado.getInt(4)
            }while (resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return tienda
    }

    fun updateTienda(id: Int?, nombre: String?, direccion: String?, ciudad: String?, numEmpleados: Int?): Boolean{
        val db = writableDatabase
        var content = ContentValues()
        content.put("id", id)
        content.put("nombre", nombre)
        content.put("direccion", direccion)
        content.put("ciudad", ciudad)
        content.put("numEmpleados", numEmpleados)
        val resultado = db.update(tableT, content, "id = ?", arrayOf(id.toString()))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun deleteTienda(id:Int?): Boolean{
        val db = writableDatabase
        val resultado = db.delete(tableT, "id=?", arrayOf(id.toString()))
        db.close()

        return if(resultado.toInt()==-1) false else true
    }

    fun insertProducto(idTienda: Int?, descripcion: String?,  precio: Double?, descuento: Int?, fechaDeElaboracion: Long?): Boolean{
        val db = writableDatabase
        val content = ContentValues()
        content.put("idTienda", idTienda)
        content.put("descripcion", descripcion)
        content.put("fechaDeElaboracion", fechaDeElaboracion)
        content.put("precio", precio)
        content.put("descuento", descuento)
        val resultado = db.insert(tableP, null, content)
        db.close()
        return if(resultado.toInt()==-1) false else true
    }


    fun readProductos(idTienda: Int){
        BDExamen.arregloProductos.clear()
        val db = readableDatabase
        val script = "SELECT * FROM ${tableP} WHERE idTienda = ?"
        val resultado = db.rawQuery(script, arrayOf(idTienda.toString()))
        if(resultado.moveToFirst()){
            do {
                val producto = Producto()
                producto.idProducto = resultado.getInt(0)
                producto.idTienda = resultado.getInt(1)
                producto.descripcion = resultado.getString(2)
                producto.fechaDeElaboracion = resultado.getLong(3)
                producto.precio = resultado.getDouble(4)
                producto.descuento = resultado.getInt(5)
                BDExamen.arregloProductos.add(producto)
            }while(resultado.moveToNext())
        }

    }

    fun updateProducto(idProducto: Int?, idTienda: Int?, descripcion: String?, fechaDeElaboracion: Long?, precio: Double?, descuento: Int?): Boolean{
        val db = writableDatabase
        var content = ContentValues()
        content.put("id", idProducto)
        content.put("idTienda", idTienda)
        content.put("descripcion", descripcion)
        content.put("fechaDeElaboracion", fechaDeElaboracion)
        content.put("precio", precio)
        content.put("descuento", descuento)
        val resultado = db.update(tableP, content, "id=? and idTienda=?", arrayOf(idProducto.toString(), idTienda.toString() ))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun deleteProducto(id: Int?): Boolean{
        val db = writableDatabase
        val resultado = db.delete(tableP, "id = ?", arrayOf(id.toString()))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

}