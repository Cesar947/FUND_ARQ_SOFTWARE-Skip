package com.simplife.skip.activities

import com.simplife.skip.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.adapter.ResenasRecyclerAdapter
import com.simplife.skip.models.Viaje
import com.simplife.skip.util.Resenas_Data
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import kotlinx.android.synthetic.main.viaje_list_item.view.*

class ViajeDetail : AppCompatActivity() {

    private lateinit var back_btn: ImageButton
    private  lateinit var viajeAdapter: ResenasRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viaje_detail)

        val viaje = intent.getSerializableExtra("via") as Viaje
        back_btn = findViewById(R.id.detback_button)

        viajedetail_author.setText(viaje.username)
        viajedetail_title.setText(viaje.publish)
        viajedetail_text.setText(viaje.body)
        viajedetail_destino.setText(viaje.destiny)
        viajedetail_origen.setText(viaje.source)
        viajedetail_hora_destino.setText(viaje.horaDestino)
        viajedetail_hora_origen.setText(viaje.horaSalida)
        viajedetail_val.setText(viaje.valoracion.toString())

        //Lista de Resenas
        recyclerView = findViewById(R.id.recycler_resenas_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        //val topSpacingDecoration = TopSpacingItemDecoration(30)
        //recyclerView.addItemDecoration(topSpacingDecoration)
        viajeAdapter = ResenasRecyclerAdapter()
        recyclerView.adapter = viajeAdapter
        val data = Resenas_Data.createResenas()
        viajeAdapter.submitList(data)
        //Fin de lista de Resenas


        back_btn.setOnClickListener{
            finish()
        }

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(applicationContext)
            .applyDefaultRequestOptions(requestOptions)
            .load(viaje.image)
            .into(viajedetail_image)

    }
}
