package com.example.proyecto_iib

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class pedidoAdapter(
    private val contexto: Context,
    private val lista: ArrayList<Pedido>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<pedidoAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var numero_pedido: TextView
        var pedidos_list: ListView
        var fecha_pedido: TextView
        init {
            numero_pedido = view.findViewById(R.id.tv_number_pedido)
            pedidos_list = view.findViewById(R.id.lv_pedidos_pedido)
            fecha_pedido = view.findViewById(R.id.tv_fecha_pedido)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pedidoAdapter.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_carrito_adapter,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pedidoActual = this.lista[position]
        holder.numero_pedido.text = "Pedido " + position
        holder.fecha_pedido.text = pedidoActual.fecha.toString()
        var arreglo = pedidoActual.list_pedidos
        //List View
        val adaptador = ArrayAdapter(
            contexto, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        holder.pedidos_list.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

}