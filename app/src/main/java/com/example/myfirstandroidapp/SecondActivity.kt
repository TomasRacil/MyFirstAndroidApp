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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.Socket

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
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        // 3. Nastavení RecyclerView
        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Načteme nastavení serveru
        val sharedPref = getSharedPreferences("ChatPrefs", MODE_PRIVATE)
        val serverIp = sharedPref.getString("SERVER_IP", "10.0.2.2") ?: "10.0.2.2"
        val serverPort = sharedPref.getInt("SERVER_PORT", 6000)

        // 4. Logika tlačítka Odeslat
        btnSend.setOnClickListener {
            val text = etInput.text.toString()

            if (text.isNotEmpty()) {
                progressBar.visibility = View.VISIBLE
                btnSend.isEnabled = false

                // 1. Spustíme Coroutinu na IO vlákně (pozadí)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        // 2. Vytvoříme spojení (toto blokuje, proto jsme na pozadí)
                        val socket = Socket()
                        socket.connect(InetSocketAddress(serverIp, serverPort), 2000)
                        val writer = PrintWriter(socket.getOutputStream(), true)

                        // 3. Pošleme zprávu ve formátu "Jméno: Text"
                        writer.println("$userName: $text")

                        // Zavřeme spojení (pro tento jednoduchý příklad odeslání)
                        // V další lekci (čtení) to budeme muset nechat otevřené.
                        socket.close()

                        // 4. Pokud se to povedlo, aktualizujeme UI
                        // UI se SMÍ měnit jen na hlavním vlákně (Main)
                        withContext(Dispatchers.Main) {
                            val newMessage = Message(sender = "Já", text = text)
                            messageList.add(newMessage)
                            messageAdapter.notifyItemInserted(messageList.size - 1)
                            recyclerView.scrollToPosition(messageList.size - 1)
                            etInput.text.clear()
                        }

                    } catch (e: Exception) {
                        // 5. Chyba (server nejede, špatná IP...)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@SecondActivity,
                                "Chyba připojení k $serverIp:$serverPort\n${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                            // Pro výukové účely přidáme zprávu lokálně, i když selže síť,
                            // aby student viděl, co napsal (volitelné).
                            // Tady to necháme jen vypsat chybu.
                        }
                        e.printStackTrace()
                    } finally {
                        // Toto proběhne VŽDY (ať už úspěch nebo chyba)
                        // Vrátíme UI do původního stavu
                        withContext(Dispatchers.Main) {
                            progressBar.visibility = View.GONE
                            btnSend.isEnabled = true
                        }
                    }
                }
            }
        }
    }
}