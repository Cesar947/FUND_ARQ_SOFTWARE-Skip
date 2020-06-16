package com.simplife.skip.util

import com.simplife.skip.models.Viaje

class DataSource {
        companion object{

            fun createDataSet(): ArrayList<Viaje>{
                val list = ArrayList<Viaje>()
                list.add(
                    Viaje(
                        "Viaje rikolino",
                        "Nuevo viaje",
                        "https://vignette.wikia.nocookie.net/virtualyoutuber/images/6/62/%E3%81%91%E3%82%82%E3%81%BF%E3%81%BF%E3%81%8A%E3%83%BC%E3%81%93%E3%81%8F%E5%9B%BD%E5%96%B6%E6%94%BE%E9%80%81_icon.png/revision/latest?cb=20180515175123&path-prefix=es",
                        "UPC San Miguel",
                        "Carabayllo",
                        "Sebastian Pinillos",
                        "11:00 am",
                        "09:00 am",
                        "8 Nov"
                    )
                )
                list.add(
                    Viaje(
                        "Viaje hasta la u",
                        "Unanse a mi viaje",
                        "https://i.pinimg.com/originals/01/03/bc/0103bcf1cba96de3d213f46c8fa5a21c.jpg",
                        "UPC Monterrico",
                        "Magdalena del Mar",
                        "Cesar Pizarro",
                        "01:00 pm",
                        "10:00 am",
                        "8 Nov"
                    )
                )
                list.add(
                    Viaje(
                        "Viaje a la diversion",
                        "Unanse bbs",
                        "https://vignette.wikia.nocookie.net/youtubepedia/images/4/4c/Dedsonrie.jpg/revision/latest?cb=20200225204711&path-prefix=es",
                        "UPC San Isidro",
                        "Mexico",
                        "Ded Reviil",
                        "11:00 am",
                        "8:00 am",
                        "9 Nov"
                    )
                )
                list.add(
                    Viaje(
                        "Mi nombre es optimus prime",
                        "Mi nombre es optimus prime",
                        "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2020/04/optimus-prime-transformers-1911133.jpg",
                        "UPC San Isidro",
                        "Cybertron",
                        "Optimus Prime",
                        "11:00 am",
                        "8:00 am",
                        "9 Nov"
                    )
                )
                list.add(
                    Viaje(
                        "Mi nombre es optimus prime",
                        "Hey hey, Shirogane san",
                        "https://i.ytimg.com/vi/XWRD8FD9V1w/maxresdefault.jpg",
                        "UPC San Isidro",
                        "Academia",
                        "Hayasaka Shiro",
                        "11:00 am",
                        "8:00 am",
                        "9 Nov"
                    )
                )
                return list
            }
        }
}
