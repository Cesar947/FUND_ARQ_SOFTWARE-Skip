package com.simplife.skip.models

import java.io.Serializable

data class SolicitudRequest (
    var mensaje: String,
    var pasajeroId: Int,
    var viajeId: Int,
    var paradaEncuentroId: Long

    ): Serializable{
    override fun toString(): String {
        return "SolicitudRequest(mensaje='$mensaje', pasajeroId=$pasajeroId, viajeId=$viajeId, paradaEncuentroId=$paradaEncuentroId)"
    }

}