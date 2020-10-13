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
import com.simplife.skip.models.Auto
import com.simplife.skip.models.RegisterEntity
import com.simplife.skip.models.SignUpRequest
import com.simplife.skip.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign


class VehicleInformationFragment : Fragment() {

    lateinit var usuarioService: UsuarioApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vehicle_information, container, false)

        val confirmButton: Button = view.findViewById(R.id.confirm_button_driver_register)
        val backButton: Button = view.findViewById(R.id.back_button_driver_vehicle)

        val placa: EditText = view.findViewById(R.id.etPlaca)
        val nAsientos: EditText = view.findViewById(R.id.etAsientos)
        val nAnhosUso: EditText = view.findViewById(R.id.etAnhosUso)
        val poliza: EditText = view.findViewById(R.id.etPoliza)
        val marca: EditText = view.findViewById(R.id.etMarca)
        val modelo: EditText = view.findViewById(R.id.etModelo)

        var request: SignUpRequest? = null
        arguments?.let{
            request = it.getSerializable("signUpRequest") as SignUpRequest
        }

        usuarioService = ApiClient.retrofit.create(UsuarioApiService::class.java)

        backButton.setOnClickListener {
            val fmt = requireActivity().supportFragmentManager
            fmt.popBackStack(null, 0)
        }

        confirmButton.setOnClickListener {
            request?.let{
                val auto = Auto(
                    placa.text.toString(),
                    poliza.text.toString().toInt(),
                    marca.text.toString(),
                    modelo.text.toString(),
                    nAsientos.text.toString().toString().toInt(),
                    nAnhosUso.text.toString().toString().toInt())
                it.auto = auto
                registrarConductor(it)
            }

        }


        return view
    }

    fun registrarConductor(signUpRequest: SignUpRequest){
        usuarioService.registroUsuario(signUpRequest).enqueue(object: Callback<RegisterEntity>{
            override fun onFailure(call: Call<RegisterEntity>, t: Throwable) {
                Log.i("Fallo en registro",  "F")
            }

            override fun onResponse(
                call: Call<RegisterEntity>,
                response: Response<RegisterEntity>
            ) {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        })

    }

}