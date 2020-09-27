package com.simplife.skip.models

import java.io.Serializable

class Solicitud (
    var user: String,
    var message: String,
    var image: String,
    var time: String
):Serializable{
    override fun toString(): String {
        return "Solicitud(user='$user', message='$message', image='$image', time='$time')"
    }
}