package com.simplife.skip.models

data class Viaje (
    var title: String,
    var body: String,
    var image: String,
    var destiny: String,
    var source: String,
    var username: String
) {

    override fun toString(): String {
        return "BlogPost(title='$title', image='$image', username='$username')"
    }


}

