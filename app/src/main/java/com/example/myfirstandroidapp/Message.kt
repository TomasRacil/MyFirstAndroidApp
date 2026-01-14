package com.example.myfirstandroidapp

/**
 * Datová třída reprezentující jednu zprávu v chatu.
 *
 * @param sender Jméno odesílatele (např. "Já", "Server", "Pepa")
 * @param text Samotný text zprávy
 * @param timestamp Čas odeslání
 */
data class Message(
    val sender: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)