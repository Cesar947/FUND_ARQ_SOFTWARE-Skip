package com.simplife.skip.models

import java.io.Serializable

class Rol(

    var id: Long,
    var nombre: String,
    var estadoTabla: Boolean
) : Serializable {
}