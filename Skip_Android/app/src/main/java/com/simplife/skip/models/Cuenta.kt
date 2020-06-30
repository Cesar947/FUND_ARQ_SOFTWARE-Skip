package com.simplife.skip.models

import java.io.Serializable

class Cuenta (
    var Id: Long,
    var codigoUpc: String,
    var correoUPC: String,
    var contrasena: String,
    var estadoTabla: Boolean,
    var roles: Set<Rol>
) :Serializable {

    override fun toString(): String {
        return "Usuasrio(user='$codigoUpc', image='$correoUPC', rol='$contrasena')"
    }
}