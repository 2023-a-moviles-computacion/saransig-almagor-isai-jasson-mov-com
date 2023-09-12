package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class EstadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estados)
        iniciarRecyclerView()
        val chats_btn = findViewById<Button>(R.id.id_chats3)
        chats_btn
            .setOnClickListener {
                irActividad(MainActivity::class.java)
            }
    }

    fun iniciarRecyclerView(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_estados)
        val adaptador = EstadosAdapter(this,
            BDEstados.arregloEstados,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }
}