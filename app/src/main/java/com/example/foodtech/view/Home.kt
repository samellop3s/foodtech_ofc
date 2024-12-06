package com.example.foodtech.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.foodtech.R
import com.example.foodtech.SuporteCliente
import com.example.foodtech.viewmodel.ClienteViewModel

class Home : AppCompatActivity() {
    private lateinit var clienteViewModel: ClienteViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        clienteViewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)

        val editTextEmail: EditText = findViewById(R.id.editCadEmail)
        val editTextSenha: EditText = findViewById(R.id.editSenhaCad)

        val buttonSuporteCliente: Button = findViewById(R.id.buttonSuporte)
        buttonSuporteCliente.setOnClickListener{
            val intent = Intent(this, SuporteCliente::class.java)
            startActivity(intent)
        }

        val buttonCadastro: Button = findViewById(R.id.buttonCadastro)
        buttonCadastro.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val editCadEmail = editTextEmail.text.toString().trim()
            val editSenhaCad = editTextSenha.text.toString().trim()
            validarLogin(editCadEmail, editSenhaCad, buttonLogin)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|bol\\.com\\.br|me\\.com|icloud\\.com|outlook\\.com)$")
        return regex.matches(email)
    }

    private fun navegarPraPedidos(email: String) {
        val intent = Intent(this, Pedidos::class.java)
        startActivity(intent)
    }

    private fun validarLogin(email: String, senha: String, view: View) {
        if (!isValidEmail(email)) {
            mensagem(view, "Email inválido!")
            return
        }

        clienteViewModel.getClienteByEmail(email).observe(this) { cliente ->
            if (cliente != null) {
                if (cliente.senha == senha) {
                    navegarPraPedidos(cliente.email)
                } else {
                    mensagem(view, "Senha incorreta!")
                }
            } else {
                mensagem(view, "Usuário não encontrado!")
            }
        }
    }

    private fun mensagem(view: View, texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }
}