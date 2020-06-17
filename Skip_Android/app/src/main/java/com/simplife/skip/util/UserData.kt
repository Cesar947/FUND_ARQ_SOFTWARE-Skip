package com.simplife.skip.util

import com.simplife.skip.models.Usuario

class UserData {
    companion object{

        fun createUsers(): ArrayList<Usuario>{
            val list = ArrayList<Usuario>()
            list.add(
                Usuario(
                    "https://dicomania.com/wp-content/uploads/2019/05/maxresdefault-12.jpg",
                    "1234",
                    "conductor",
                    "cesar@gmail.com",
                    "Cesar947"

                )
            )
            list.add(
               Usuario(
                    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/as-ketchum-pokemon-1557471113.png?resize=480:*",
                    "chino",
                    "pasajero",
                    "pepino@gmail.com",
                    "Pepino"

                )
            )
            return list
        }
    }
}