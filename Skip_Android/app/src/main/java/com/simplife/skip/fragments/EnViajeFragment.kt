package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplife.skip.R
import com.simplife.skip.adapter.PasajerosEnViajeRecyclerAdapter
import com.simplife.skip.models.PasajeroEnViaje


class EnViajeFragment : Fragment() {

    lateinit var recyclerPasajerosEnViaje: RecyclerView
    lateinit var adapterPasajerosEnViaje: PasajerosEnViajeRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_en_viaje, container, false)

        recyclerPasajerosEnViaje = view.findViewById(R.id.recycler_pasajeros_a_recoger)
        recyclerPasajerosEnViaje.layoutManager = LinearLayoutManager(context)

        adapterPasajerosEnViaje = PasajerosEnViajeRecyclerAdapter(requireContext())

        val lista: ArrayList<PasajeroEnViaje> = arrayListOf(
            PasajeroEnViaje(1, "César", "Av. Sucre 333", "Por recoger", "https://wp-content.bluebus.com.br/wp-content/uploads/2017/03/31142426/twitter-novo-avatar-padrao-2017-bluebus.png")
        )
        recyclerPasajerosEnViaje.adapter = adapterPasajerosEnViaje
        adapterPasajerosEnViaje.submitList(lista)


        return view
    }

}