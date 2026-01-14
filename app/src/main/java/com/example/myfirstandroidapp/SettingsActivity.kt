package com.example.myfirstandroidapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        // Edge-to-Edge odsazení
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etIp = findViewById<EditText>(R.id.etIpAddress)
        val etPort = findViewById<EditText>(R.id.etPort)
        val btnSave = findViewById<Button>(R.id.btnSaveSettings)

        // 1. Otevřeme "notýsek" se jménem "ChatPrefs"
        val sharedPref = getSharedPreferences("ChatPrefs", MODE_PRIVATE)

        // 2. Načteme uložené hodnoty (nebo výchozí, pokud nic není)
        val savedIp = sharedPref.getString("SERVER_IP", "10.0.2.2") // 10.0.2.2 je alias pro localhost na PC v emulátoru
        val savedPort = sharedPref.getInt("SERVER_PORT", 6000)

        // 3. Zobrazíme je v políčkách
        etIp.setText(savedIp)
        etPort.setText(savedPort.toString())

        // 4. Uložení při kliknutí
        btnSave.setOnClickListener {
            val newIp = etIp.text.toString()
            val newPortString = etPort.text.toString()

            if (newIp.isNotEmpty() && newPortString.isNotEmpty()) {
                val newPort = newPortString.toIntOrNull()

                if (newPort != null) {
                    // Otevřeme editor pro zápis
                    sharedPref.edit {
                        putString("SERVER_IP", newIp)
                        putInt("SERVER_PORT", newPort)
                        // Asynchronní uložení (nezasekne aplikaci)
                    }

                    Toast.makeText(this, "Nastavení uloženo", Toast.LENGTH_SHORT).show()
                    finish() // Zavře aktivitu a vrátí nás zpět
                } else {
                    etPort.error = "Neplatný port"
                }
            } else {
                Toast.makeText(this, "Vyplňte vše", Toast.LENGTH_SHORT).show()
            }
        }
    }
}