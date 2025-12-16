package com.example.myfirstandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    // Seznam zpráv (data)
    private val messageList = mutableListOf<Message>()

    // Adapter (propojení dat s grafikou)
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Získáme přihlášené jméno z předchozí aktivity
        val userName = intent.getStringExtra("USER_NAME") ?: "Neznámý"
        title = "Chat: $userName" // Nastaví titulek okna

        // 2. Inicializace UI prvků
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMessages)
        val etInput: EditText = findViewById(R.id.etMessageInput)
        val btnSend: Button = findViewById(R.id.btnSend)

        // 3. Nastavení RecyclerView
        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. Logika tlačítka Odeslat
        btnSend.setOnClickListener {
            val text = etInput.text.toString()

            if (text.isNotEmpty()) {
                // Vytvoříme novou zprávu
                val newMessage = Message(sender = userName, text = text)

                // Přidáme do seznamu
                messageList.add(newMessage)

                // Řekneme adaptéru, že přibyla položka na konci (aby ji vykreslil)
                messageAdapter.notifyItemInserted(messageList.size - 1)

                // Odscrolujeme dolů na novou zprávu
                recyclerView.scrollToPosition(messageList.size - 1)


                // Vyčistíme pole pro psaní
                etInput.text.clear()
            }
        }
    }
}