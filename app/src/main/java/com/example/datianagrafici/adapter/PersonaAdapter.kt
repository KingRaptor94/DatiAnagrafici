package com.example.datianagrafici.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datianagrafici.R
import com.example.datianagrafici.entity.Persona

// Definizione della classe PersonaViewHolder, che estende la classe RecyclerView.ViewHolder
// e contiene riferimenti ai TextView dell'elemento della lista
class PersonaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView
    private val surname: TextView
    private val data: TextView
    private val city: TextView

    // Inizializzazione dei riferimenti ai TextView
    init {
        name = view.findViewById(R.id.name)
        surname = view.findViewById(R.id.surname)
        data = view.findViewById(R.id.birthDate)
        city = view.findViewById(R.id.birthCity)
    }

    // Metodo che imposta i valori dei TextView dell'elemento della lista a partire da una Persona
    fun showPersona(persona: Persona) {
        name.text = persona.name
        surname.text = persona.surname
        data.text = persona.birthdate
        city.text = persona.city
    }
}

// Definizione della classe PersonaAdapter, che estende la classe RecyclerView.Adapter e gestisce
// la creazione degli elementi della lista e l'associazione di essi ai dati
class PersonaAdapter(private val model: List<Persona>) : RecyclerView.Adapter<PersonaViewHolder>() {

    // Metodo che viene chiamato quando il RecyclerView ha bisogno di creare un nuovo elemento della lista
    // Si crea una View a partire dal layout "person_list_item" e si passa alla costruzione di un nuovo PersonaViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val template = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.person_list_item, parent, false)
        return PersonaViewHolder(template)
    }

    // Metodo che restituisce la dimensione del modello (cioè il numero di elementi della lista)
    override fun getItemCount(): Int = model.size

    // Metodo che viene chiamato quando un elemento della lista deve essere visualizzato
    // Si associa l'elemento della lista alla posizione corrente ai dati della PersonaViewHolder
    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.showPersona(model[position])
    }
}
