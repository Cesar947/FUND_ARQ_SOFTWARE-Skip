package com.simplife.skip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.R
import com.simplife.skip.models.Viaje
import kotlinx.android.synthetic.main.viaje_list_item.view.*

class ViajeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items : List<Viaje> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViajeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viaje_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViajeViewHolder->{
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(viajeList: List<Viaje>){
        items = viajeList
    }

    class ViajeViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val userImage = itemView.viaje_image
        val viajeTitle = itemView.viaje_title
        val author = itemView.viaje_author
        val viajeText = itemView.viaje_text
        val viajeSource = itemView.viaje_origen
        val viajeDestiny = itemView.viaje_destino
        val viajeHoraOrigen = itemView.viaje_hora_origen
        val viajeHoraDestino = itemView.viaje_hora_destino

        fun bind(viaje: Viaje){
            viajeTitle.setText(viaje.publish)
            author.setText(viaje.username)
            viajeText.setText(viaje.body)
            viajeSource.setText(viaje.source)
            viajeDestiny.setText(viaje.destiny)
            viajeHoraDestino.setText(viaje.horaDestino)
            viajeHoraOrigen.setText(viaje.horaSalida)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(viaje.image)
                .into(userImage)
        }
    }


}
