package com.simplife.skip.models

data class ViajeInicio(
    var id: Int,
    var nombres: String,
    var fechaPublicacion:String,
    var descripcion: String,
    var paradas: List<Parada>,
    var horaInicio: String,
    var horaFin: String
)