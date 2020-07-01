package com.simplife.skip.models

import java.io.Serializable

class Parada (
    var ubicacion: String,
    var latitud: Float,
    var longitud: Float

) :Serializable {

    override fun toString(): String {
        return "Resena(title='$ubicacion', image='$latitud', username='$longitud')"
    }
}