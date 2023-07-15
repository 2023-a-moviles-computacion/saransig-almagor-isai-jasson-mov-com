package com.example.examen01

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

private lateinit var tiendasAdapter: ArrayAdapter<Tienda>
class ListTienda : AppCompatActivity() {
    var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Tienda>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tienda)

        //Boton para Insertar Tienda
        val btn_insert_tienda = findViewById<Button>(R.id.btn_ingreso)
        btn_insert_tienda
            .setOnClickListener {
                guardarTienda(FormTienda::class.java)
            }

        COBaseDatos.coBDatos?.readTiendas()

        val listView = findViewById<ListView>(R.id.lv_tienda)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BDExamen.arregloTiendas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)

        // Listener para los elementos del ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            val idTienda = adaptador.getItem(position)
            irListProducto(ListProducto::class.java, idTienda?.idTienda)
        }


    }
    private var tiendaSeleccionada: Tienda? = null

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
        tiendaSeleccionada = BDExamen.arregloTiendas[info.position]

    }

    fun irListProducto(
        clase: Class<*>,
        idTienda: Int?
    ){
        val intent = Intent(this, clase)
        intent.putExtra("idTienda", idTienda)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.mi_editar -> {
                irEditTienda(EditTienda::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                val idOb = tiendaSeleccionada?.idTienda

                abrirDialogo {
                    COBaseDatos.coBDatos!!.deleteTienda(idOb)
                    COBaseDatos.coBDatos!!.readTiendas()
                    adaptador.notifyDataSetChanged()
                }

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }




    fun abrirDialogo(callback: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar"
        ) { dialog, which ->
            callback.invoke() // Ejecutar el callback al aceptar
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    fun irEditTienda(
        clase: Class <*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre", tiendaSeleccionada?.nombre)
        intentExplicito.putExtra("direccion", tiendaSeleccionada?.direccion)
        intentExplicito.putExtra("ciudad", tiendaSeleccionada?.ciudad)
        intentExplicito.putExtra("numEmpleados", tiendaSeleccionada?.numeroEmpleados)
        respEditTienda.launch(intentExplicito)
    }


    fun guardarTienda(
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
                    val nombre = result.data?.getStringExtra("nombre")
                    val direccion = result.data?.getStringExtra("direccion")
                    val ciudad = result.data?.getStringExtra("ciudad")
                    val numEmpleados = result.data?.getIntExtra("numEmpleados", 0)
                    COBaseDatos.coBDatos!!.insertTienda(
                        nombre,
                        direccion,
                        ciudad,
                        numEmpleados
                    )
                    COBaseDatos.coBDatos!!.readTiendas()
                    adaptador.notifyDataSetChanged()
                }
            }
        }

    val respEditTienda =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //Logica de Negocio
                    val nombre = result.data?.getStringExtra("nombre")
                    val direccion = result.data?.getStringExtra("direccion")
                    val ciudad = result.data?.getStringExtra("ciudad")
                    val numEmpleados = result.data?.getIntExtra("numEmpleados", 0)
                    COBaseDatos.coBDatos!!.updateTienda(
                        tiendaSeleccionada?.idTienda,
                        nombre,
                        direccion,
                        ciudad,
                        numEmpleados
                    )
                    COBaseDatos.coBDatos!!.readTiendas()
                    adaptador.notifyDataSetChanged()
                }
            }
        }
}