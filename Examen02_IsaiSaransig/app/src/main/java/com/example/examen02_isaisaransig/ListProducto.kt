package com.example.examen02_isaisaransig

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class ListProducto : AppCompatActivity() {
    /*
    var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Producto>
    var idTienda = 0

    var arreglo: ArrayList<Producto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_producto)
        arreglo = FirebaseSettings.arregloProductos
        //Obtener ID Tienda
        idTienda = intent.getIntExtra("idTienda", 0)
        val nameTienda = findViewById<TextView>(R.id.nameTienda)
        nameTienda.setText(COBaseDatos.coBDatos!!.readTiendaByID(idTienda).nombre)

        //Boton para insertar Producto
        val btn_insert_product = findViewById<Button>(R.id.btn_inst_product)
        btn_insert_product
            .setOnClickListener {
                guardarProducto(FormProducto::class.java)
            }

        COBaseDatos.coBDatos?.readProductos(idTienda)

        val listView = findViewById<ListView>(R.id.lv_producto)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            BDExamen.arregloProductos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)


    }

    private var productoSeleccionado: Producto? = null

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        productoSeleccionado = BDExamen.arregloProductos[info.position]

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.mi_editar -> {
                irEditProducto(EditProducto::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                val idOb = productoSeleccionado?.idProducto

                abrirDialogo {
                    COBaseDatos.coBDatos!!.deleteProducto(idOb)
                    COBaseDatos.coBDatos!!.readProductos(idTienda)
                    adaptador.notifyDataSetChanged()
                }

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irEditProducto(
        clase: Class <*>
    ){
        val intentProducto = Intent(this, clase)
        intentProducto.putExtra("descripcion", productoSeleccionado?.descripcion)
        intentProducto.putExtra("precio", productoSeleccionado?.precio)
        intentProducto.putExtra("descuento", productoSeleccionado?.descuento)
        intentProducto.putExtra("fechaDeElaboracion", productoSeleccionado?.fechaDeElaboracion)
        respEditProducto.launch(intentProducto)
    }

    val respEditProducto =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val descripcion = result.data?.getStringExtra("descripcion")
                    val precio = result.data?.getDoubleExtra("precio", 0.0)
                    val descuento = result.data?.getIntExtra("descuento", 0)
                    val fechaElaboracion = result.data?.getLongExtra("fechaDeElaboracion", 0)
                    COBaseDatos.coBDatos!!.updateProducto(
                        productoSeleccionado?.idProducto,
                        idTienda,
                        descripcion,
                        fechaElaboracion,
                        precio,
                        descuento
                    )
                    COBaseDatos.coBDatos!!.readProductos(idTienda)
                    adaptador.notifyDataSetChanged()
                }
            }
        }

    fun abrirDialogo(callback: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar"
        ) { dialog, which ->
            callback.invoke()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }



    fun guardarProducto(
        clase: Class <*>
    ){
        val intentExplicito = Intent(this, clase)
        respInsert.launch(intentExplicito)
    }

    val respInsert =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val descripcion = result.data?.getStringExtra("descripcion")
                    val precio = result.data?.getDoubleExtra("precio", 0.0)
                    val descuento = result.data?.getIntExtra("descuento", 0)
                    val fechaElab = result.data?.getLongExtra("fechaElab", 0)
                    COBaseDatos.coBDatos!!.insertProducto(
                        idTienda,
                        descripcion,
                        precio,
                        descuento,
                        fechaElab
                    )
                    COBaseDatos.coBDatos!!.readProductos(idTienda)
                    adaptador.notifyDataSetChanged()
                }
            }
        }*/
}