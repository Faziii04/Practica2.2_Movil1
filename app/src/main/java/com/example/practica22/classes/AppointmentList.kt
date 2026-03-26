package com.example.practica22.classes

object AppointmentList {

    private val listaGlobalCitas = mutableListOf<Appointment>()

    fun agregar(cita: Appointment) {
        listaGlobalCitas.add(cita)
    }

    fun obtenerTodas(): List<Appointment> {
        return listaGlobalCitas
    }

    fun obtenerCitasPorId (identificador: Int, tipo: String) : List<Appointment> {
        val listaAns = mutableListOf<Appointment>()
        if (tipo == "mascota") {
            listaGlobalCitas.forEach { appointment ->
                if (appointment.mascota_id == identificador) { listaAns.add(appointment)}
            }
            return listaAns
        }
        else if (tipo == "doctor") {
            listaGlobalCitas.forEach { appointment ->
                if (appointment.ci == identificador) { listaAns.add(appointment) }
            }
            return listaAns
        }
        return listaAns
    }



    fun limpiar() {
        listaGlobalCitas.clear()
    }
}
