package com.example.practica22

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GestionDoctoresActivity : AppCompatActivity() {
    private lateinit var adapter: DoctorAdapter
    private var previousDoctorCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gestion_doctores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvDoctores = findViewById<RecyclerView>(R.id.rvDoctores)
        val btnNuevo = findViewById<Button>(R.id.btnNuevoDoctor)

        adapter = DoctorAdapter(
            doctores = DataHolder.listaDoctores,
            onEditClick = { position ->
                startActivity(
                    Intent(this, RegistroDoctor::class.java).putExtra(RegistroDoctor.EXTRA_DOCTOR_INDEX, position)
                )
            },
            onDeleteClick = { position ->
                DataHolder.listaDoctores.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, DataHolder.listaDoctores.size)
            }
        )

        rvDoctores.layoutManager = LinearLayoutManager(this)
        rvDoctores.adapter = adapter
        previousDoctorCount = DataHolder.listaDoctores.size

        btnNuevo.setOnClickListener {
            startActivity(Intent(this, RegistroDoctor::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val currentCount = DataHolder.listaDoctores.size
        when {
            currentCount > previousDoctorCount -> {
                adapter.notifyItemRangeInserted(previousDoctorCount, currentCount - previousDoctorCount)
            }
            currentCount < previousDoctorCount -> {
                adapter.notifyItemRangeRemoved(currentCount, previousDoctorCount - currentCount)
            }
            else -> {
                adapter.notifyItemRangeChanged(0, currentCount)
            }
        }
        previousDoctorCount = currentCount
    }
}