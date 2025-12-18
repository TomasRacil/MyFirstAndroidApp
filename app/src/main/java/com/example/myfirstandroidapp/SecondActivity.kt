package com.example.myfirstandroidapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    // Použijeme delegáta viewModels() pro získání ChatViewModel
    private val chatViewModel: ChatViewModel by viewModels()

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
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        // Nastavení Adapteru (začínáme s prázdným seznamem)
        messageAdapter = MessageAdapter(emptyList())
        recyclerView.adapter = messageAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        // --- 1. Načtení nastavení a připojení ---
        val sharedPref = getSharedPreferences("ChatPrefs", MODE_PRIVATE)
        val serverIp = sharedPref.getString("SERVER_IP", "10.0.2.2") ?: "10.0.2.2"
        val serverPort = sharedPref.getInt("SERVER_PORT", 6000)

        // Řekneme ViewModelu, ať se připojí (pokud už není)
        chatViewModel.connect(serverIp, serverPort, userName)

        // --- 2. Sledování změn (Observer) ---

        // Sledujeme seznam zpráv
        chatViewModel.messages.observe(this) { messages ->
            // Když přijdou nové zprávy, aktualizujeme adaptér
            // Pro jednoduchost vytvoříme nový adaptér nebo bychom mohli aktualizovat data uvnitř
            // Tady uděláme "rychlou" variantu - update dat v adapteru by byl čistší, ale toto stačí.
            messageAdapter = MessageAdapter(messages)
            recyclerView.adapter = messageAdapter
            recyclerView.scrollToPosition(messages.size - 1)
        }

        // Sledujeme stav připojení
        chatViewModel.connectionStatus.observe(this) { status ->
            if (status.startsWith("Chyba") || status == "Odpojeno") {
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            } else if (status == "Připojování...") {
                progressBar.visibility = View.VISIBLE
            } else if (status == "Připojeno") {
                progressBar.visibility = View.GONE
            }
        }

        // --- 3. Odesílání ---
        btnSend.setOnClickListener {
            val text = etInput.text.toString()
            if (text.isNotEmpty()) {
                chatViewModel.sendMessage(userName, text)
                etInput.text.clear()
            }
        }
    }
}