package com.jarvis.ai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.ai.R
import com.jarvis.ai.data.ChatMessage

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_USER = 1
        private const val TYPE_JARVIS = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUser) TYPE_USER else TYPE_JARVIS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_user, parent, false)
                UserViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_jarvis, parent, false)
                JarvisViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is UserViewHolder -> holder.bind(message)
            is JarvisViewHolder -> holder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.textMessage)
        private val timestampText: TextView = itemView.findViewById(R.id.textTimestamp)

        fun bind(message: ChatMessage) {
            messageText.text = message.message
            timestampText.text = message.timestamp
        }
    }

    class JarvisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.textMessage)
        private val timestampText: TextView = itemView.findViewById(R.id.textTimestamp)

        fun bind(message: ChatMessage) {
            messageText.text = message.message
            timestampText.text = message.timestamp
        }
    }
}
