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

    fun insertTienda(tienda: Tienda): Boolean{
        val db = writableDatabase
        val content = ContentValues()
        content.put("nombre", tienda.nombre)
        content.put("direccion", tienda.direccion)
        content.put("ciudad", tienda.ciudad)
        content.put("numEmpleados", tienda.numeroEmpleados)
        val resultado = db.insert(tableT, null, content)
        db.close()
        return if(resultado.toInt()==-1) false else true
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

    fun updateTienda(id: Int, nombre: String, direccion: String, ciudad: String, numEmpleados: Int): Boolean{
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

    fun deleteTienda(id:Int): Boolean{
        val db = writableDatabase
        val resultado = db.delete(tableT, "id=?", arrayOf(id.toString()))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun insertProducto(producto: Producto): Boolean{
        val db = writableDatabase
        val content = ContentValues()
        content.put("idTienda", producto.idTienda)
        content.put("descripcion", producto.descripcion)
        content.put("fechaDeElaboracion", producto.fechaDeElaboracion.toString())
        content.put("precio", producto.precio)
        content.put("descuento", if (producto.descuento) 1 else 0)
        val resultado = db.insert(tableP, null, content)
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun readProductoByID(id: Int): Producto{
        val db = readableDatabase
        val script = "SELECT * FROM " + tableP + " WHERE id = ?"
        val respuesta = db.rawQuery(script, arrayOf(id.toString()))
        var producto = Producto()
        if (respuesta.moveToFirst()){
            do {
                producto.idProducto = respuesta.getInt(0)
                producto.idTienda = respuesta.getInt(1)
                producto.descripcion = respuesta.getString(2)
                //fecha
                producto.precio = respuesta.getDouble(4)
                //descuento
            }while (respuesta.moveToNext())
        }
        respuesta.close()
        db.close()
        return producto
    }

    fun updateProducto(idProducto: Int, idTienda: Int, descripcion: String, fechaDeElaboracion: Date, precio: Double, descuento: Boolean): Boolean{
        val db = writableDatabase
        var content = ContentValues()
        content.put("id", idProducto)
        content.put("idTienda", idTienda)
        content.put("descripcion", descripcion)
        content.put("fechaDeElaboracion", fechaDeElaboracion.toString())
        content.put("precio", precio)
        content.put("descuento", descuento)
        val resultado = db.update(tableP, content, "id=? and idTienda=?", arrayOf(idProducto.toString()))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

    fun deleteProducto(id: Int): Boolean{
        val db = writableDatabase
        val resultado = db.delete(tableP, "id = ?", arrayOf(id.toString()))
        db.close()
        return if(resultado.toInt()==-1) false else true
    }

}