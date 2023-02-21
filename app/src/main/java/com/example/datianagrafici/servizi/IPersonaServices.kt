package com.example.datianagrafici.servizi

import com.example.datianagrafici.entity.Persona

// Interfaccia per la gestione dei dati delle persone
interface IPersonaServices {

    // Aggiunge una nuova persona alla lista
    fun addPerson(person: Persona): Unit

    // Rimuove una persona dalla lista
    fun removePerson(person: Persona): Unit

    // Restituisce una lista di tutte le persone
    fun getAllPersons(): List<Persona>
}