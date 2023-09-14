package com.example.proyecto_iib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class carritoAdapter(
    private val contexto: Context,
    private val lista: ArrayList<Carrito>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<carritoAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tipo: TextView
        var talla: TextView
        var color: TextView
        var precio: TextView
        var count: TextView
        var imageRopa: ImageView
        var btn_more: Button
        var btn_less: Button
        init {
            tipo = view.findViewById(R.id.tv_tipo_carrito)
            talla = view.findViewById(R.id.tv_talla_carrito)
            color = view.findViewById(R.id.tv_color_carrito)
            precio = view.findViewById(R.id.tv_precio_carrito)
            count = view.findViewById(R.id.tv_count_carrito)
            imageRopa = view.findViewById(R.id.img_ropa_carrito)
            btn_more = view.findViewById(R.id.btn_more_carrito)
            btn_less = view.findViewById(R.id.btn_less_carrito)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): carritoAdapter.MyViewHolder {
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
        val carritoActual = this.lista[position]
        holder.tipo.text = carritoActual.ropa!!.tipo
        holder.talla.text = carritoActual.ropa!!.talla
        holder.color.text = carritoActual.ropa!!.colorRopa
        holder.precio.text = carritoActual.ropa!!.precio.toString()

        val imageResource = holder.itemView.context.resources.getIdentifier(
            carritoActual.ropa!!.imagen,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.imageRopa.setImageResource(imageResource)

        holder.btn_more.setOnClickListener {
            holder.count.text = (holder.count.text.toString().toInt() + 1).toString()
        }
        holder.btn_less.setOnClickListener {
            if (holder.count.text.toString().toInt() > 0){
                holder.count.text = (holder.count.text.toString().toInt() - 1).toString()
            }

        }
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}