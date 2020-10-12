package com.simplife.skip.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.simplife.skip.R
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.models.SignUpRequest
import com.simplife.skip.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFormGeneralFragment : Fragment() {

    val fragmentId = 2
    lateinit var botonAtras: Button
    lateinit var botonSiguiente: Button
    lateinit var botonConfirmar: Button
    lateinit var usuarioService: UsuarioApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_form_general, container, false)

        var rol = ""
        arguments?.let{
            rol = it.getString("rol").toString()
        }

        botonAtras = view.findViewById(R.id.back_button_general_register)
        botonSiguiente = view.findViewById(R.id.next_button_general_register)
        botonConfirmar = view.findViewById(R.id.confirm_button_general_register)

        botonAtras.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack(null,0)
        }
        usuarioService = ApiClient.retrofit.create(UsuarioApiService::class.java)

        val fragmentDriver = RegisterDriverInformationFragment()

        val etName = view.findViewById<EditText>(R.id.etName)
        val etLastName = view.findViewById<EditText>(R.id.etLastName)
        val etDni = view.findViewById<EditText>(R.id.etDni)
        val etCodigo = view.findViewById<EditText>(R.id.etCode)
        val etContrasena = view.findViewById<EditText>(R.id.etPassword)
        val etSede = view.findViewById<EditText>(R.id.etCampus)


        if(rol.equals("ROL_PASAJERO")){
            botonConfirmar.visibility = View.VISIBLE
            botonSiguiente.visibility = View.GONE
            botonConfirmar.setOnClickListener {
            val request = SignUpRequest(
                etCodigo.text.toString(),
                etContrasena.text.toString(),
                etDni.text.toString(),
                etName.text.toString(),
                etLastName.text.toString(),
                etSede.text.toString(),
                "https://wp-content.bluebus.com.br/wp-content/uploads/2017/03/31142426/twitter-novo-avatar-padrao-2017-bluebus.png",
                listOf("ROL_PASAJERO").toSet())

                registrarUsuarioPasajero(request)
            }


        } else if(rol.equals("ROL_CONDUCTOR")){

            botonSiguiente.visibility = View.VISIBLE
            botonConfirmar.visibility = View.GONE
            botonSiguiente.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("codigo", etCodigo.text.toString())
                bundle.putString("contrasena", etContrasena.text.toString())
                bundle.putString("dni", etDni.text.toString())
                bundle.putString("nombres", etName.text.toString())
                bundle.putString("apellidos", etLastName.text.toString())
                bundle.putString("sede", etSede.text.toString())
                bundle.putString("imagen", "https://wp-content.bluebus.com.br/wp-content/uploads/2017/03/31142426/twitter-novo-avatar-padrao-2017-bluebus.png")
                fragmentDriver.arguments = bundle
                loadFragment(fragmentDriver)
            }
        }







        return view
    }

    fun registrarUsuarioPasajero(form: SignUpRequest){
        usuarioService.registroUsuario(form).enqueue(object: Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("Fallo en registro", "No se pudo registrar")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "Inicie sesión con su cuenta", Toast.LENGTH_SHORT).show()
                }
            }
        })
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