package com.simplife.skip.models

import java.io.Serializable

data class Viaje (
    var id: Long,
    var conductor: Usuario,
    var ruta: Ruta,
    var descripcion: String,
    var fechaPublicacion: String,
    var fechaViaje: String,
    var horaInicio: String,
    var horaLlegada: String,
    var visualizacionHabilitada: Boolean,
    var numeroPasajeros: Int,
    var estadoViaje: String,
    var horaPublicacion: String,
    var estadoTabla: Boolean

) : Serializable {

    override fun toString(): String {
        return "BlogPost(title='$fechaViaje', image='$fechaPublicacion', username='$horaPublicacion')"
    }


}

