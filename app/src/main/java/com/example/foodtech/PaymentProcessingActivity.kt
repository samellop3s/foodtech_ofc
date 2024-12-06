package com.example.foodtech

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class PaymentProcessingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_processing)

                val paymentMethod = intent.getStringExtra("paymentMethod") ?: "Desconhecido"
                val processingText = findViewById<TextView>(R.id.txtProcessing)
                processingText.text = "Processando pagamento via $paymentMethod..."

                // Simula um atraso no processamento
                android.os.Handler().postDelayed({
                    val paymentSuccess = Random.nextBoolean()
                    val resultIntent = Intent(this, PaymentResultActivity::class.java)
                    resultIntent.putExtra("paymentSuccess", paymentSuccess)
                    startActivity(resultIntent)
                    finish()
                }, 2000) // Atraso de 2 segundos

    }
}