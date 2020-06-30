package com.simplife.skip.models

import java.io.Serializable

class Usuario (
    var image: String,
    var password: String,
    var rol: String,
    var email: String,
    var nombre: String,
    var username: String,
    var sede: String,
    var facebook: String,
    var ubicacion: String
): Serializable{

    override fun toString(): String {
        return "Usuasrio(user='$username', image='$image', rol='$rol')"
    }
}