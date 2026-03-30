package com.example.practica22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica22.classes.Doctor

class DoctorAdapter(
    private val doctores: MutableList<Doctor>,
    private val onEditClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvDoctorNombre)
        val tvEspecialidad: TextView = view.findViewById(R.id.tvDoctorEspecialidad)
        val btnEliminar: ImageButton = view.findViewById(R.id.btnEliminarDoctor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctores[position]
        holder.tvNombre.text = holder.itemView.context.getString(
            R.string.doctor_nombre_formato,
            doctor.primer_nombre,
            doctor.segundo_nombre
        ).trim()
        holder.tvEspecialidad.text = doctor.especialidad

        // Tap the row to edit the selected doctor.
        holder.itemView.setOnClickListener {
            val adapterPosition = holder.bindingAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) onEditClick(adapterPosition)
        }

        holder.btnEliminar.setOnClickListener {
            val adapterPosition = holder.bindingAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) onDeleteClick(adapterPosition)
        }
    }

    override fun getItemCount() = doctores.size
}