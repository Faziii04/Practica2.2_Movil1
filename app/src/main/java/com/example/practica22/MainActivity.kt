package com.example.practica22

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var doctoresBoton: Button
    lateinit var mascotasBoton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        doctoresBoton = findViewById(R.id.registerDoctorButton)
        mascotasBoton = findViewById(R.id.registerPetButton)

        doctoresBoton.setOnClickListener {
            cambiarRegistroDoctores()
        }

        mascotasBoton.setOnClickListener {
            cambiarRegistroMascotas()
        }



    }

    private fun cambiarRegistroDoctores() {
        val newActivity = Intent(this, RegistroDoctor::class.java)
        startActivity(newActivity)
    }

    private fun cambiarRegistroMascotas() {
        val newActivity = Intent(this, RegistroDoctor::class.java)
        startActivity(newActivity)
    }


}