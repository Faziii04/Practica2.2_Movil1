package com.example.practica22

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica22.classes.Mascota

class RegistroMascota : AppCompatActivity() {

    private lateinit var ivPetPhoto: ImageView
    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            ivPetPhoto.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_mascota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivPetPhoto = findViewById(R.id.ivPetPhoto)
        val etNombre = findViewById<EditText>(R.id.etNombreMascota)
        val etEspecie = findViewById<EditText>(R.id.etEspecie)
        val etRaza = findViewById<EditText>(R.id.etRaza)
        val etEdad = findViewById<EditText>(R.id.etEdadMascota)
        val etPeso = findViewById<EditText>(R.id.etPesoMascota)
        val etPassword = findViewById<EditText>(R.id.etPasswordMascota)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarMascota)

        ivPetPhoto.setOnClickListener {
            // Extra points: Load from gallery
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImage.launch(intent)
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val especie = etEspecie.text.toString()
            val raza = etRaza.text.toString()
            val password = etPassword.text.toString()

            if (nombre.isNotEmpty() && password.isNotEmpty()) {
                val mascota = Mascota(
                    DataHolder.listaMascotas.size + 1,
                    nombre,
                    etPeso.text.toString().toIntOrNull() ?: 0,
                    0, // altura not in form but in class
                    etEdad.text.toString().toIntOrNull() ?: 0,
                    password,
                    especie,
                    raza
                )

                DataHolder.listaMascotas.add(mascota)
                Toast.makeText(this, "Mascota registrada: $nombre", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}