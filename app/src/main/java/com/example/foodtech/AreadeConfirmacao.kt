package com.example.foodtech

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class AreadeConfirmacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_areadeconfirmacao)

        val btnCreditCard = findViewById<Button>(R.id.btnCreditCard)
        val btnPix = findViewById<Button>(R.id.btnPix)


        btnCreditCard.setOnClickListener { initiateFakePayment("Cartão de Crédito") }
        btnPix.setOnClickListener { initiateFakePayment("Pix") }
       
    }

    private fun initiateFakePayment(method: String) {
        val intent = Intent(this, PaymentProcessingActivity::class.java)
        intent.putExtra("paymentMethod", method)
        startActivity(intent)
    }

}
