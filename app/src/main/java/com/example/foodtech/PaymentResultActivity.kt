package com.example.foodtech

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_result)

        val paymentSuccess = intent.getBooleanExtra("paymentSuccess", false)
        val resultText = findViewById<TextView>(R.id.txtPaymentResult)

        if (paymentSuccess) {
            resultText.text = "Pagamento aprovado com sucesso!"
        } else {
            resultText.text = "O pagamento foi recusado. Tente novamente."
        }

    }
}