package com.example.proyecto_iib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class pedidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        iniciarRecyclerView()
    }

    fun iniciarRecyclerView(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_pedido)
        val adaptador = pedidoAdapter(this,
            FirestoreDB.arregloPedido,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }

}