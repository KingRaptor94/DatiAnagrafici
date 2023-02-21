package com.example.datianagrafici.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.datianagrafici.entity.Persona

@Dao
interface PersonaDao {

    /*salvare una persona su database*/

    @Insert
    fun save(persona: Persona)

    /*Recuperare o dati personali di una persona.*/

    @Query("SELECT * FROM Persona ")
    fun getAll(): List<Persona>
}