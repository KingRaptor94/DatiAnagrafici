package com.example.datianagrafici.servizi

import com.example.datianagrafici.entity.Gender
import com.example.datianagrafici.entity.Persona

class PersonaServices : IPersonaServices {
    // creazione una lista di oggetti Persona come variabile di classe per gestire i dati
    companion object {
        val personList: MutableList<Persona> = mutableListOf(
            // Persona("Nicolò", "Paracchini", "16/06/94","Novara", "NO", Gender.Male),
        )
    }

    // implementazione del metodo addPerson dell'interfaccia IPersonaServices
    override fun addPerson(person: Persona) {
        personList.add(person) // aggiunge una persona alla lista
    }

    // implementazione del metodo removePerson dell'interfaccia IPersonaServices
    override fun removePerson(person: Persona) {
        personList.remove(person) // rimuove una persona dalla lista
    }

    // implementazione del metodo getAllPersons dell'interfaccia IPersonaServices
    override fun getAllPersons(): List<Persona> = personList // restituisce la lista di tutte le persone
}
