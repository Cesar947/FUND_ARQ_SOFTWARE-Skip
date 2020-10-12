package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simplife.skip.R
import com.simplife.skip.models.SignUpRequest


class RegisterDriverInformationFragment : Fragment() {

    val fragmentId = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_driver_information, container, false)


        var request: SignUpRequest
        arguments?.let{
            val codigo = it.getString("codigo")!!
            val contrasena = it.getString("contrasena")!!
            val dni = it.getString("dni")!!
            val nombres = it.getString("nombres")!!
            val apellidos = it.getString("apellidos")!!
            val sede = it.getString("sede")!!
            val imagen = it.getString("imagen")!!

            request = SignUpRequest(codigo, contrasena, dni, nombres, apellidos, sede, imagen, listOf("ROL_PASAJERO", "ROL_CONDUCTOR").toSet())

        }




        return view
    }


}