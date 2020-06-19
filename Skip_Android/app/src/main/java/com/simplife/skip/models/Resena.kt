package com.simplife.skip.models

data class Resena (
    var body: String,
    var image: String,
    var username: String,
    var publish: String,
    var valoracion: Double
){

    override fun toString(): String {
        return "Resena(title='$body', image='$image', username='$username')"
    }


}