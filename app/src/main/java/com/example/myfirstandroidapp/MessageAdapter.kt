package com.example.myfirstandroidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter starající se o zobrazování seznamu zpráv v RecyclerView.
 */
class MessageAdapter(
    private val messages: List<Message>
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    /**
     * Třída ViewHolder drží odkazy na prvky v layoutu (item_message.xml),
     * abychom je nemuseli hledat pořád dokola.
     */
    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSender: TextView = view.findViewById(R.id.tvSender)
        val tvContent: TextView = view.findViewById(R.id.tvMessageContent)
    }

    /**
     * Vytvoří nový vzhled pro položku (zavolá se jen tolikrát, kolik se vejde na obrazovku + pár navíc).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    /**
     * Naplní ViewHolder daty pro konkrétní pozici v seznamu.
     */
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.tvSender.text = message.sender
        holder.tvContent.text = message.text
    }

    /**
     * Vrátí celkový počet zpráv.
     */
    override fun getItemCount(): Int {
        return messages.size
    }
}