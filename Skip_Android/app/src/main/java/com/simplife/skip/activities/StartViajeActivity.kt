package com.simplife.skip.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplife.skip.R
import com.simplife.skip.adapter.PasajerosRecyclerAdapter
import com.simplife.skip.adapter.ViajeRecyclerAdapter
import com.simplife.skip.models.Viaje
import kotlinx.android.synthetic.main.activity_start_viaje.*
import kotlinx.android.synthetic.main.activity_viaje_detail.*

class StartViajeActivity : AppCompatActivity() {

    private  lateinit var pasajeroAdapter: PasajerosRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_viaje)

        val viaje = intent.getSerializableExtra("via") as Viaje

        startviaje_origen.setText(viaje.source)
        startviaje_horaorigen.setText(viaje.horaSalida)
        startviaje_destino.setText(viaje.destiny)
        startviaje_horadestino.setText(viaje.horaDestino)

        recyclerView = findViewById(R.id.recycler_pasajeros)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //val topSpacingDecoration = TopSpacingItemDecoration(30)
        //recyclerView.addItemDecoration(topSpacingDecoration)
        pasajeroAdapter = PasajerosRecyclerAdapter()
        recyclerView.adapter = pasajeroAdapter
        val data = com.simplife.skip.util.UserData.createUsers()
        pasajeroAdapter.submitList(data)

    }
}
