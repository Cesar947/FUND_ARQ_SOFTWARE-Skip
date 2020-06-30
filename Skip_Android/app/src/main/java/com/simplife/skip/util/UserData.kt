package com.simplife.skip.util

import com.simplife.skip.models.Usuario

class UserData {
    companion object{

        fun createUsers(): ArrayList<Usuario>{
            val list = ArrayList<Usuario>()
            list.add(
                Usuario(
                    "https://i.ytimg.com/vi/YOYloOO80qk/maxresdefault.jpg",
                    "1234",
                    "conductor",
                    "cesar@gmail.com",
                    "Cesar Pizarro",
                    "Cesar947",
                    "UPC San Miguel",
                    "/Cesar947",
                    "Magdalena del Mar, Lima"

                )
            )
            list.add(
               Usuario(
                    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/as-ketchum-pokemon-1557471113.png?resize=480:*",
                    "chino",
                    "pasajero",
                    "pepino@gmail.com",
                   "Sebastian Pinillos",
                    "Pepino",
                   "UPC San Miguel",
                   "/Sebastpz",
                   "San Miguel, Lima"

                )
            )
            return list
        }
    }
}