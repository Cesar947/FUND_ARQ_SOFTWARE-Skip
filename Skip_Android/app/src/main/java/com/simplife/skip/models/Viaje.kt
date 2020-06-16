package com.simplife.skip.models

import java.io.Serializable

data class Viaje (
    var title: String,
    var body: String,
    var image: String,
    var destiny: String,
    var source: String,
    var username: String,
    var horaDestino: String,
    var horaSalida: String,
    var publish: String,
    var valoracion: Double
) : Serializable {

    override fun toString(): String {
        return "BlogPost(title='$title', image='$image', username='$username')"
    }


}

