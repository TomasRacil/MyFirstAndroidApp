package com.example.myfirstandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Načtení dat z Intentu (který nám poslala MainActivity)
        val username = intent.getStringExtra("USER_NAME")

        // Zobrazení jména
        val tvWelcome = findViewById<TextView>(R.id.tvWelcomeUser)
        tvWelcome.text = "Vítej, $username!"

        // Tlačítko zpět
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Ukončí tuto aktivitu a vrátí nás na předchozí
        }
    }
}