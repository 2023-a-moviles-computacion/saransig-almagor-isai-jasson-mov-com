package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class coleccionActivity : AppCompatActivity() {
    var coleccion = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coleccion)
        coleccion = intent.getStringExtra("nombre").toString()
        rvCamiseta()
        rvPantalones()
        rvChaqueta()
        rvHoodie()
        //menu
        val btn_catalogo = findViewById<ImageButton>(R.id.ibtn_catalogo)
        btn_catalogo.setOnClickListener {
            irActividad(catalogoActivity::class.java)
        }

    }

    fun rvCamiseta(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_camiseta)

        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloCamiseta,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readColeccionCamiseta("HubStyle",  adaptador, coleccion)
        adaptador.notifyDataSetChanged()
    }
    fun rvPantalones(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_pantalon)

        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloPantalones,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readColeccionPantalon("HubStyle",  adaptador, coleccion)
        adaptador.notifyDataSetChanged()
    }
    fun rvChaqueta(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_chaquetas)

        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloChaqueta,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readColeccionChaqueta("HubStyle", adaptador, coleccion)
        adaptador.notifyDataSetChanged()
    }
    fun rvHoodie(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_hoodies)

        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloHoodies,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readColeccionHoodie("HubStyle", adaptador, coleccion)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }
}