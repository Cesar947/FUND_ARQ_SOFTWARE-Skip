package com.simplife.skip.models

import java.io.Serializable

class Parada (
    var ubicacion: String,
    var latitud: Float,
    var longitud: Float

) :Serializable {
    override fun toString(): String {
        return "Parada(ubicacion='$ubicacion', latitud=$latitud, longitud=$longitud)"
    }
}