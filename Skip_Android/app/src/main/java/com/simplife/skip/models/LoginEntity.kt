package com.simplife.skip.models

import java.io.Serializable
import java.security.acl.LastOwnerException

class LoginEntity (
    var token: String,
    var cuentaid: Long,
    var usuarioId: Long,
    var codigo: String,
    var email: String,
    var roles: List<String>
) : Serializable {

    override fun toString(): String {
        return "Usuario(user='$codigo', image='$email', rol='$usuarioId')"
    }
}