package com.simplife.skip.models

import java.io.Serializable

data class Auto(
    var placa: String,
    var polizaSoat: String,
    var marca: String,
    var modelo: String,
    var limitePersonas: Int,
    var anhosUso: Int


): Serializable