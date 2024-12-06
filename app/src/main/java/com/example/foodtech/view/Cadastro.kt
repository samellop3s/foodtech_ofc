package com.example.foodtech.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodtech.MainActivity
import com.example.foodtech.R
import com.example.foodtech.entities.Cliente
import com.example.foodtech.viewmodel.ClienteViewModel

class Cadastro : AppCompatActivity() {
    private val clienteViewModel: ClienteViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val button: Button = findViewById(R.id.btSalvaCad)
        val emailEditText: EditText = findViewById(R.id.editCadEmail)
        val senhaEditText: EditText = findViewById(R.id.editSenhaCad)

        window.statusBarColor = ContextCompat.getColor(this, R.color.cofee)

        button.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()
            realizarCadastro(email, senha)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun realizarCadastro(email: String, senha: String) {
        when {
            email.isEmpty() -> {
                Toast.makeText(this, "Por favor, preencha o email.", Toast.LENGTH_SHORT).show()
            }
            !isValidEmail(email) -> {
                Toast.makeText(this, "Email inválido.", Toast.LENGTH_SHORT).show()
            }
            senha.isEmpty() -> {
                Toast.makeText(this, "Por favor, preencha a senha.", Toast.LENGTH_SHORT).show()
            }
            senha.length < 6 -> {
                Toast.makeText(this, "A senha precisa ter pelo menos 6 caracteres.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val cliente = Cliente(
                    id_cliente = 0,
                    nome = "",
                    email = email,
                    senha = senha,
                    telefone = ""
                )
                clienteViewModel.addCliente(cliente)
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
