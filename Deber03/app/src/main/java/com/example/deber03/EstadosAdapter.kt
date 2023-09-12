package com.example.deber03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstadosAdapter(
    private val contexto: Context,
    private val lista: ArrayList<Estados>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<EstadosAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombreTV: TextView
        var horaTV: TextView
        val estado: ImageView
        val btn_estado: Button
        init {
            nombreTV = view.findViewById(R.id.id_name_est)
            horaTV = view.findViewById(R.id.id_time_est)
            estado = view.findViewById(R.id.id_estado)
            btn_estado = view.findViewById(R.id.btn_estado)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstadosAdapter.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_estados_adapter,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val estadoActual = this.lista[position]
        holder.nombreTV.text = estadoActual.nombrePersona
        holder.horaTV.text = estadoActual.tiempo

        val imageResource = holder.itemView.context.resources.getIdentifier(
            estadoActual.imagen,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.estado.setImageResource(imageResource)
        holder.btn_estado
            .setOnClickListener {
                abrirImagen(holder)
            }
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    fun abrirImagen(holder: MyViewHolder){
        holder.nombreTV.text = "cambio"
    }


}