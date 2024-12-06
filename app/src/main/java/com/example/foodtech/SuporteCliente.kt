package com.example.foodtech

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SuporteCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suporte_cliente)

        val button = findViewById<Button>(R.id.btnWhats)
        button.setOnClickListener {
            val number = "5511942108013"
            val message = "Olá, preciso de ajuda."
            val url = "https://api.whatsapp.com/send?phone=$number&text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


    }
}