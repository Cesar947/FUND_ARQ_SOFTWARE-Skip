package com.simplife.skip.models

import java.io.Serializable

class Ruta(

    var id: Long,
    var tiempoEstimado: String,
    var sentido: Boolean,
    var distancia: Float,
    var estadoTabla: Boolean


): Serializable {


}