package com.example.practica22

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegistroDoctor: Button
    private lateinit var btnRegistroMascota: Button
    private lateinit var btnGestionDoctores: Button
    private lateinit var btnLogin: Button
    private lateinit var fabHistorial: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRegistroDoctor = findViewById(R.id.registerDoctorButton)
        btnRegistroMascota = findViewById(R.id.registerPetButton)
        btnGestionDoctores = findViewById(R.id.btnGestionDoctores)
        btnLogin = findViewById(R.id.btnLoginDoctor)
        fabHistorial = findViewById(R.id.fabHistory)

        btnRegistroDoctor.setOnClickListener {
            startActivity(Intent(this, RegistroDoctor::class.java))
        }

        btnRegistroMascota.setOnClickListener {
            startActivity(Intent(this, RegistroMascota::class.java))
        }

        btnGestionDoctores.setOnClickListener {
            startActivity(Intent(this, GestionDoctoresActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        fabHistorial.setOnClickListener {
            startActivity(Intent(this, RegistroHistorial::class.java))
        }
    }
}