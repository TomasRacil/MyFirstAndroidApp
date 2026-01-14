package com.example.myfirstandroidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.Socket

class ChatViewModel : ViewModel() {

    // Seznam zpráv, který sleduje Aktivita (LiveData)
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    // Pomocný seznam pro vnitřní úpravy
    private val currentMessages = mutableListOf<Message>()

    // Stav připojení (např. pro zobrazení "Připojeno" nebo chyby)
    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus

    private var socket: Socket? = null
    private var writer: PrintWriter? = null

    /**
     * Funkce pro připojení k serveru a spuštění naslouchání
     */
    fun connect(ip: String, port: Int, userName: String) {
        // Spustíme na pozadí (IO vlákno)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (socket != null && !socket!!.isClosed) {
                    return@launch // Už jsme připojeni
                }

                _connectionStatus.postValue("Připojování...")

                socket = Socket()
                socket?.connect(InetSocketAddress(ip, port), 2000)

                writer = PrintWriter(socket!!.getOutputStream(), true)

                _connectionStatus.postValue("Připojeno")

                // Odeslání první uvítací zprávy (volitelné, server to může vyžadovat)
                // writer?.println("$userName se připojil")

                // --- SMYČKA PRO ČTENÍ (NASLOUCHÁNÍ) ---
                val reader = BufferedReader(InputStreamReader(socket!!.getInputStream()))

                while (true) {
                    // Tato řádka "blokuje" - čeká, dokud nepřijdou data
                    val line = reader.readLine() ?: break // Pokud je null, server ukončil spojení

                    // Zpracování příchozí zprávy
                    // Předpokládáme formát "Odesílatel: Zpráva"
                    // Pro jednoduchost to zatím vezmeme celé jako text
                    val parts = line.split(":", limit = 2)
                    val sender = if (parts.size > 1) parts[0].trim() else "Server"
                    val content = if (parts.size > 1) parts[1].trim() else line

                    val newMessage = Message(sender, content)

                    // Přidáme do seznamu a pošleme do UI
                    currentMessages.add(newMessage)
                    // postValue() je bezpečné volat z pozadí (na rozdíl od setValue)
                    _messages.postValue(ArrayList(currentMessages))
                }

            } catch (e: Exception) {
                _connectionStatus.postValue("Chyba: ${e.message}")
                e.printStackTrace()
            } finally {
                socket?.close()
                _connectionStatus.postValue("Odpojeno")
            }
        }
    }

    /**
     * Funkce pro odeslání zprávy
     */
    fun sendMessage(userName: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (writer != null) {
                    writer?.println("$userName: $text")

                    // Přidáme si vlastní zprávu hned do seznamu (aby byla vidět okamžitě)
                    // Některé chaty čekají, až se vrátí ze serveru, my ji ukážeme hned.
                    val myMessage = Message("Já", text)
                    currentMessages.add(myMessage)
                    _messages.postValue(ArrayList(currentMessages))
                }
            } catch (e: Exception) {
                _connectionStatus.postValue("Chyba odeslání: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Úklid při ukončení
        try {
            socket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}