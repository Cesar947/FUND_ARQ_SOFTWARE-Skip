package com.simplife.skip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.R
import com.simplife.skip.models.Usuario
import kotlinx.android.synthetic.main.pasajero_list_item.view.*

class PasajerosRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<Usuario> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MiPasajeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pasajero_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MiPasajeroViewHolder->{
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(viajeList: List<Usuario>){
        items = viajeList
    }

    class MiPasajeroViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val pasajeroName = itemView.pasajero_name
        val pasajeroImage = itemView.pasajero_image
        val pasajeroParada = itemView.pasajero_punto_encuentro

        fun bind(usuario: Usuario){
            pasajeroName.setText(usuario.nombres)
            pasajeroParada.setText(usuario.ubicacion)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(usuario.imagen)
                .into(pasajeroImage)

        }

    }


}