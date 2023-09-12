package com.example.deber03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MensajesAdapter(
    private val contexto: Context,
    private val lista: ArrayList<Mensaje>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<MensajesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombreTV: TextView
        var mensajeTV: TextView
        var horaTV: TextView
        var notificationIV: ImageView
        val foto: ImageView
        init {
            nombreTV = view.findViewById(R.id.id_name_est)
            mensajeTV = view.findViewById(R.id.id_time_est)
            horaTV = view.findViewById(R.id.id_time)
            notificationIV = view.findViewById(R.id.id_notification)
            foto = view.findViewById(R.id.id_estado)
        }

    }
    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_mensajes_adapter,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mensajeActual = this.lista[position]
        holder.nombreTV.text = mensajeActual.nombrePersona
        holder.mensajeTV.text = mensajeActual.mensaje
        holder.horaTV.text = mensajeActual.horaEnvio
        val imageResource = holder.itemView.context.resources.getIdentifier(
            mensajeActual.imgPersona,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.foto.setImageResource(imageResource)
        if (mensajeActual.visto) {
            holder.horaTV.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.tertiary))
            holder.notificationIV.visibility = View.VISIBLE
        }
    }

    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}