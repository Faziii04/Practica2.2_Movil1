package com.example.practica22

import com.example.practica22.classes.Doctor
import com.example.practica22.classes.Mascota
import com.example.practica22.classes.Diagnostico

object DataHolder {
    val listaDoctores = mutableListOf<Doctor>()
    val listaMascotas = mutableListOf<Mascota>()
    val listaHistoriales = mutableListOf<Diagnostico>()
}