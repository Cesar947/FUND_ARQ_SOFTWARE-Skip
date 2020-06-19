package com.simplife.skip.models

import java.io.Serializable

class Usuario (
    var image: String,
    var password: String,
    var rol: String,
    var email: String,
    var username: String
): Serializable{

    override fun toString(): String {
        return "Usuasrio(user='$username', image='$image', rol='$rol')"
    }
}