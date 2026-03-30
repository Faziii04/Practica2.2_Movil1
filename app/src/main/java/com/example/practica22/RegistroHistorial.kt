package com.example.practica22

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica22.classes.Diagnostico

class RegistroHistorial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_historial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etTipoMascota = findViewById<EditText>(R.id.etTipoMascota)
        val etPeso = findViewById<EditText>(R.id.etPesoHistorial)
        val etMedidas = findViewById<EditText>(R.id.etMedidas)
        val etDolencia = findViewById<EditText>(R.id.etDolencia)
        val etDiagnostico = findViewById<EditText>(R.id.etDiagnostico)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarHistorial)

        btnGuardar.setOnClickListener {
            val tipoMascota = etTipoMascota.text.toString().trim()
            val medidas = etMedidas.text.toString().trim()
            val dolencia = etDolencia.text.toString().trim()
            val diagnostico = etDiagnostico.text.toString().trim()
            val peso = etPeso.text.toString().trim().toDoubleOrNull()

            if (tipoMascota.isEmpty() || medidas.isEmpty() || dolencia.isEmpty() || diagnostico.isEmpty() || peso == null) {
                Toast.makeText(this, "Complete todos los campos con valores validos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            DataHolder.listaHistoriales.add(
                Diagnostico(
                    tipoMascota = tipoMascota,
                    pesoKg = peso,
                    medidasCm = medidas,
                    dolencia = dolencia,
                    diagnostico = diagnostico
                )
            )

            Toast.makeText(this, "Historial guardado exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}