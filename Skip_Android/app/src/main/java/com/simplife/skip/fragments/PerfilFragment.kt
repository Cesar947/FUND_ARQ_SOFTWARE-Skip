package com.simplife.skip.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.simplife.skip.R
import com.simplife.skip.activities.Login
import com.simplife.skip.models.Usuario
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import kotlinx.android.synthetic.main.fragment_perfil.*

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {

    private lateinit var perfil_nombre: TextView
    private lateinit var perfil_sede: TextView
    private lateinit var perfil_fb: TextView
    private lateinit var perfil_ubicacion: TextView
    private lateinit var perfil_foto: ImageView
    private lateinit var cerrar_sesion: LinearLayout
    private lateinit var editar_perfil: LinearLayout
    private lateinit var opciones: CardView

    private lateinit var perfil_settings: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_perfil, container, false)

        perfil_nombre = vista.findViewById(R.id.perfil_username)
        perfil_fb = vista.findViewById(R.id.perfil_fb)
        perfil_sede = vista.findViewById(R.id.perfil_sede)
        perfil_ubicacion = vista.findViewById(R.id.perfil_ubicacion)
        perfil_foto = vista.findViewById(R.id.perfil_photo)
        cerrar_sesion = vista.findViewById(R.id.cerrar_sesion)
        editar_perfil = vista.findViewById(R.id.editar_perfil)
        opciones = vista.findViewById(R.id.opciones)

        perfil_settings = vista.findViewById(R.id.perfil_settings)

        //Recibimos data de usuario
        val usuario: Usuario = arguments?.get("user") as Usuario

        perfil_settings.setOnClickListener{
            if(opciones.visibility == View.GONE) {
                opciones.visibility = View.VISIBLE
            }
            else
                opciones.visibility = View.GONE
        }

        cerrar_sesion.setOnClickListener{
            this.activity?.finish()
            context?.startActivity(Intent(context, Login::class.java))
        }

        perfil_nombre.setText(usuario.nombre)
        perfil_ubicacion.setText(usuario.ubicacion)
        perfil_sede.setText(usuario.sede)
        perfil_fb.setText(usuario.facebook)

        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(usuario.image)
            .into(perfil_foto)


        return vista
    }

}
