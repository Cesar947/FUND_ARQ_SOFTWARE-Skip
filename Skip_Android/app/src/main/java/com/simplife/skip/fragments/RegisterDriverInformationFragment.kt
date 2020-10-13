package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.simplife.skip.R
import com.simplife.skip.models.InformacionConductor
import com.simplife.skip.models.SignUpRequest


class RegisterDriverInformationFragment : Fragment() {

    val fragmentId = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_driver_information, container, false)
        val backButton = view.findViewById<Button>(R.id.back_button_driver_info)
        val nextButton = view.findViewById<Button>(R.id.next_button_driver_info)

        val facebook = view.findViewById<EditText>(R.id.etFacebook)
        val licencia = view.findViewById<EditText>(R.id.etLicencia)
        val celular = view.findViewById<EditText>(R.id.etCelular)

        var request: SignUpRequest
        var codigo = ""
        var contrasena = ""
        var dni = ""
        var nombres = ""
        var apellidos = ""
        var sede = ""
        var imagen = ""
        arguments?.let{
            codigo = it.getString("codigo")!!
            contrasena = it.getString("contrasena")!!
            dni = it.getString("dni")!!
            nombres = it.getString("nombres")!!
            apellidos = it.getString("apellidos")!!
            sede = it.getString("sede")!!
            imagen = it.getString("imagen")!!

        }

        request = SignUpRequest(codigo, contrasena, dni, nombres, apellidos, sede, imagen, listOf("pasajero", "conductor").toSet(), null, null)

        backButton.setOnClickListener {
            val fmt = requireActivity().supportFragmentManager
            fmt.popBackStack(null, 0)
        }

        nextButton.setOnClickListener {
            val bundle = Bundle()
            val info = InformacionConductor(
                facebook.text.toString(),
                celular.text.toString(),
                licencia.text.toString())
            request.infoConductor = info
            bundle.putSerializable("signUpRequest", request)
            val fragment = VehicleInformationFragment()
            fragment.arguments = bundle
            loadFragment(fragment)

        }


        return view
    }

    fun loadFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().also{
                fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragment_container_register, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}