package com.example.practica22

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica22.classes.Doctor

class RegistroDoctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_doctor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etCi = findViewById<EditText>(R.id.etCi)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val etEspecialidad = findViewById<EditText>(R.id.etEspecialidad)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarDoctor)

        val editIndex = intent.getIntExtra(EXTRA_DOCTOR_INDEX, -1)
        val isEditMode = editIndex in DataHolder.listaDoctores.indices

        if (isEditMode) {
            val doctor = DataHolder.listaDoctores[editIndex]
            etCi.setText(doctor.ci.toString())
            etNombre.setText(doctor.primer_nombre)
            etApellido.setText(doctor.segundo_nombre)
            etEdad.setText(doctor.edad.toString())
            etEspecialidad.setText(doctor.especialidad)
            etPassword.setText(doctor.contra)
            btnRegistrar.text = getString(R.string.registro_doctor_actualizar)
        }

        btnRegistrar.setOnClickListener {
            val ci = etCi.text.toString().trim().toIntOrNull()
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val edad = etEdad.text.toString().trim().toIntOrNull()
            val especialidad = etEspecialidad.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (ci == null || edad == null || nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Revise CI, edad y campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val doctor = Doctor(ci, nombre, apellido, edad, especialidad, password)

            if (isEditMode) {
                DataHolder.listaDoctores[editIndex] = doctor
                Toast.makeText(this, "Doctor actualizado con exito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                DataHolder.listaDoctores.add(doctor)
                Toast.makeText(this, "Doctor registrado con exito", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegistroDoctor, LoginActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_DOCTOR_INDEX = "doctor_index"
    }
}