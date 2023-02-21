package com.example.datianagrafici

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.datianagrafici.adapter.PersonaAdapter
import com.example.datianagrafici.dao.PersonaDatabase
import com.example.datianagrafici.servizi.PersonaServices
import java.util.concurrent.Executors

// La classe MainActivity estende AppCompatActivity e definisce l'interfaccia utente
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Imposta il layout dell'activity come definito nel file R.layout.activity_main
        setContentView(R.layout.activity_main)

        // Aggiunge un listener al pulsante per aprire l'activity per aggiungere una nuova persona
        // Quando il pulsante viene premuto, viene creato un intent per avviare l'activity AddActivity
        findViewById<Button>(R.id.aggiungi_nuova_persona).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            this.startActivity(intent)
        }

        // Crea un'istanza di PersonaServices, che contiene la logica dell'app
        val perServ = PersonaServices()

        // Inizializza la RecyclerView e la rende un elenco verticalmente scorrevole
        findViewById<RecyclerView>(R.id.listanomi).apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    // Metodo chiamato quando l'activity diventa visibile
    override fun onResume() {
        super.onResume()

        // Ottiene la RecyclerView e, tramite l'oggetto Executors, avvia una nuova thread per ottenere i dati delle persone dal database
        findViewById<RecyclerView>(R.id.listanomi).apply {
            Executors.newSingleThreadExecutor().execute {
                val data =
                    // Costruisce un'istanza di PersonaDatabase utilizzando la classe Room e il nome del database "persona"
                    Room.databaseBuilder(this@MainActivity, PersonaDatabase::class.java, "persona")
                        // Ottiene l'oggetto PersonaDao dal database e chiama il metodo getAll() per ottenere tutti i dati delle persone
                        .build().getPersonaDao().getAll()
                runOnUiThread {
                    // Imposta l'adapter della RecyclerView utilizzando i dati delle persone ottenuti dal database
                    adapter = PersonaAdapter(data)
                }
            }
        }
    }
}
