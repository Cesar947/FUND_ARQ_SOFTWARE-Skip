package com.simplife.skip.models

import java.io.Serializable

data class SolicitudRequest (
    var mensaje: String,
    var pasajeroId: Int,
    var viajeId: Int,
    var paradaEncuentroId: Long

    ): Serializable{

}