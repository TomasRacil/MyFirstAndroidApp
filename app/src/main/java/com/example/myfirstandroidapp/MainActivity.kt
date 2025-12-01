package com.example.myfirstandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Najdeme prvky v XML podle jejich ID
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // 2. Nastavíme posluchač události kliknutí (OnClickListener)
        btnLogin.setOnClickListener {
            // Získáme text z políček
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Jednoduchá validace
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Místo Toastu vytvoříme Intent pro přechod
                // Intent(odkud, kam::class.java)
                val intent = Intent(this, SecondActivity::class.java)

                // Přibalíme data ("klíč", hodnota)
                intent.putExtra("USER_NAME", username)

                // Spustíme novou aktivitu
                startActivity(intent)
            } else {
                Toast.makeText(this, "Vyplňte prosím všechna pole.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}