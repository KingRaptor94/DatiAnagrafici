package com.example.datianagrafici

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.room.Room
import com.example.datianagrafici.dao.PersonaDatabase
import com.example.datianagrafici.entity.Gender
import com.example.datianagrafici.entity.Persona
import com.example.datianagrafici.servizi.PersonaServices
import java.util.concurrent.Executors

/**
 * Questa activity gestisce l'aggiunta di nuove persone alla lista
 */
class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)

        val perServ = PersonaServices()

        // Trova il pulsante di aggiunta e gli assegna un listener di clic
        val button: Button = findViewById(R.id.addButton)
        button.setOnClickListener {

            // Trova tutti gli elementi di input dall'interfaccia utente
            val editName: EditText = findViewById(R.id.name)
            val editSurname: EditText = findViewById(R.id.surname)
            val editDate: EditText = findViewById(R.id.birthDate)
            val genderMale: RadioButton = findViewById(R.id.maleRadio)
            val editCity: EditText = findViewById(R.id.birthCity)
            val editProvince: EditText = findViewById(R.id.birthProvince)

            // Legge i valori inseriti dall'utente
            val name: String = editName.text.toString()
            val surname: String = editSurname.text.toString()
            val birthdate: String = editDate.text.toString()
            val gender: Gender = if (genderMale.isChecked) Gender.Male else Gender.Female
            val city: String = editCity.text.toString()
            val province: String = editProvince.text.toString()

            // Crea un nuovo oggetto Persona con i dati inseriti dall'utente
            val newPerson = Persona(name, surname, birthdate, city, province, gender)

            // Stampa un messaggio di log con i dati inseriti dall'utente
            Log.d("MainActivity", "newPerson: $newPerson")

            // Salva l'oggetto Persona appena creato nel database
            Executors.newSingleThreadExecutor().execute {
                Room.databaseBuilder(this, PersonaDatabase::class.java, "persona").build()
                    .getPersonaDao().save(newPerson)
            }.also {
                finish()
            }
        }
    }
}