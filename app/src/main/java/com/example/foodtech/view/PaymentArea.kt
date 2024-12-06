package com.example.foodtech.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtech.AreadeConfirmacao
import com.example.foodtech.adapter.ItensSelecionadosAdapter
import com.example.foodtech.R

class PaymentArea : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_area)

        // Recebe o valor total passado via Intent
        val valorTotal = intent.getDoubleExtra("TOTAL_PAYMENT", 0.0) // Use a chave correta
        val textViewTotal = findViewById<TextView>(R.id.textTotal)
        textViewTotal.text = "Total a pagar: R$ %.2f".format(valorTotal) // Formata o valor para duas casas decimais

        // Recebe os itens selecionados
        val itensSelecionados = intent.getParcelableArrayListExtra<Lanche>("itens_selecionados") ?: arrayListOf() // Usando arrayListOf() para evitar NPE

        // Configura o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItensSelecionados)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItensSelecionadosAdapter(itensSelecionados)

        // Botão para continuar para a área de confirmação
        val btnGoToPayment = findViewById<Button>(R.id.btnPagamento)
        btnGoToPayment.setOnClickListener {
            val intent = Intent(this, AreadeConfirmacao::class.java)
            startActivity(intent)
        }
    }
}



