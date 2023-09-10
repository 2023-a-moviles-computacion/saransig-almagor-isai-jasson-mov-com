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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ListTienda : AppCompatActivity() {
    var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Tienda>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tienda)
        //Insertar Tienda
        val btn_insert_tienda = findViewById<Button>(R.id.btn_ingreso)
        btn_insert_tienda
            .setOnClickListener {
                guardarTienda(FormTienda::class.java)
            }

        //List View
        val listView = findViewById<ListView>(R.id.lv_tienda)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            FirebaseSettings.arregloTiendas

        )
        listView.adapter = adaptador
        COFirebase.firebase?.readTiendas(adaptador)

        //Menu
        registerForContextMenu(listView)

        // Listener para los elementos del ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            val tienda = adaptador.getItem(position)
            irListProducto(ListProducto::class.java, tienda?.nombre)
        }
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
                    var tienda = Tienda(nombre, direccion,ciudad, numEmpleados)
                    COFirebase.firebase?.createTiendas(tienda)
                    COFirebase.firebase!!.readTiendas(adaptador)
                }
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
        tiendaSeleccionada = FirebaseSettings.arregloTiendas[info.position]

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

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.mi_editar -> {
                irEditTienda(EditTienda::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                val nombreTienda = tiendaSeleccionada?.nombre

                abrirDialogo {
                    COFirebase.firebase!!.deleteTienda(nombreTienda, adaptador)
                }

                return true
            }
            else -> super.onContextItemSelected(item)
        }
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
                    COFirebase.firebase!!.updateTienda(
                        nombre,
                        direccion,
                        ciudad,
                        numEmpleados,
                        adaptador
                    )

                }
            }
        }

    fun irListProducto(
        clase: Class<*>,
        nombre: String?
    ){
        val intent = Intent(this, clase)
        intent.putExtra("nombre", nombre)
        startActivity(intent)
    }


}