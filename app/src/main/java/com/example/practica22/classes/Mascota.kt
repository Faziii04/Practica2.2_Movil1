package com.example.practica22.classes

import java.time.LocalDateTime

class Mascota(
    var id: Int,
    var nombre: String,
    var peso: Int,
    var altura: Int,
    var edad: Int,
    var contra: String,
    especie: String,
    raza: String
) : Animal(especie, raza) {


}
