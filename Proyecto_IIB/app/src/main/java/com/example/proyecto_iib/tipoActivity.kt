package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class tipoActivity : AppCompatActivity() {
    var tipo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo)
        tipo = intent.getStringExtra("nombre").toString()
        rvHombre()
        rvMujer()
        val btn_catalogo = findViewById<ImageButton>(R.id.ibtn_catalogo)
        btn_catalogo.setOnClickListener {
            irActividad(catalogoActivity::class.java)
        }
    }

    fun rvHombre(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_coleccion_hombre)
        FirestoreDB.arregloHombre.clear()
        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloHombre,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readTipoHombre("HubStyle",  adaptador, tipo)
        adaptador.notifyDataSetChanged()
    }

    fun rvMujer(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_coleccion_mujer)
        FirestoreDB.arregloMujer.clear()
        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloMujer,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readTipoMujer("HubStyle",  adaptador, tipo)
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