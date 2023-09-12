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

    var idItemSeleccionado = 0


    private lateinit var adaptador: ArrayAdapter<Producto>
    var nombreTienda = ""

    var arreglo: ArrayList<Producto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_producto)
        arreglo = FirebaseSettings.arregloProductos
        //Obtener Nombre Tienda
        nombreTienda = intent.getStringExtra("nombre").toString()

        //Colocar nombre tienda en la parte superior
        val nameTienda = findViewById<TextView>(R.id.nameTienda)
        nameTienda.setText(nombreTienda)

        //Boton para insertar Producto

        val btn_insert_product = findViewById<Button>(R.id.btn_inst_product)
        btn_insert_product
            .setOnClickListener {
                guardarProducto(FormProducto::class.java)
            }



        val listView = findViewById<ListView>(R.id.lv_producto)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            FirebaseSettings.arregloProductos
        )
        listView.adapter = adaptador
        COFirebase.firebase?.readProductos(nombreTienda, adaptador)

        registerForContextMenu(listView)


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
                    val producto = Producto(descripcion, fechaElab, precio, descuento)
                    COFirebase.firebase!!.insertProducto(
                        nombreTienda,
                        adaptador,
                        producto
                    )
                }
            }
        }

/*
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



    */
}