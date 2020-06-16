package com.simplife.skip.activities

import com.simplife.skip.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.models.Viaje
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import kotlinx.android.synthetic.main.viaje_list_item.view.*

class ViajeDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viaje_detail)

        val viaje = intent.getSerializableExtra("via") as Viaje

        viajedetail_author.setText(viaje.username)
        viajedetail_title.setText(viaje.publish)
        viajedetail_text.setText(viaje.publish)
        viajedetail_destino.setText(viaje.destiny)
        viajedetail_origen.setText(viaje.source)
        viajedetail_hora_destino.setText(viaje.horaDestino)
        viajedetail_hora_origen.setText(viaje.horaSalida)
        viajedetail_val.setText(viaje.valoracion.toString())

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(applicationContext)
            .applyDefaultRequestOptions(requestOptions)
            .load(viaje.image)
            .into(viajedetail_image)

    }
}
