package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarRecyclerView()
        val btn_estados = findViewById<Button>(R.id.id_estados)
        btn_estados
            .setOnClickListener {
                irActividad(EstadosActivity::class.java)
            }
    }

    fun iniciarRecyclerView(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_mensajes)
        val adaptador = MensajesAdapter(this,
            BDMensajes.arregloMensajes,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
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