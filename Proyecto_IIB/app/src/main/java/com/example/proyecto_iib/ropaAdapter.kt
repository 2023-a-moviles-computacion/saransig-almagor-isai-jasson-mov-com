package com.example.proyecto_iib

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ropaAdapter(
    private val contexto: Context,
    private val lista: ArrayList<Ropa>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<ropaAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var ropaImg: ImageView
        var tipo: TextView
        var precio: TextView
        init {
            ropaImg = view.findViewById(R.id.img_ropa_adapter)
            tipo = view.findViewById(R.id.tv_tipo_adapter)
            precio = view.findViewById(R.id.tv_price_adapter)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ropaAdapter.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_ropa_adapter,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ropaActual = this.lista[position]
        holder.tipo.text = ropaActual.tipo.toString()
        holder.precio.text = ropaActual.precio.toString()

        val imageResource = holder.itemView.context.resources.getIdentifier(
            ropaActual.imagen,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.ropaImg.setImageResource(imageResource)
        holder.ropaImg.setOnClickListener{
            val intent = Intent(contexto, detalleActivity::class.java)
            intent.putExtra("codigo", ropaActual.codigo)
            contexto.startActivity(intent)

            // Cierra la actividad actual
            (contexto as AppCompatActivity).finish()
        }
    }


    override fun getItemCount(): Int {
        return this.lista.size
    }

}