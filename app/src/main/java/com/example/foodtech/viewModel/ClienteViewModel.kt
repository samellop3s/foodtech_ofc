package com.example.foodtech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodtech.database.ClienteDatabase
import com.example.foodtech.entities.Cliente
import com.example.foodtech.repository.ClienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClienteViewModel(application: Application) : AndroidViewModel(application) {

    // Expor readAllData para ser acessado pela UI
    val readAllData: LiveData<List<Cliente>>
    private val repository: ClienteRepository

    init {
        val clienteDao = ClienteDatabase.getDatabase(application).clienteDao()
        repository = ClienteRepository(clienteDao)
        readAllData = repository.readAllData
    }

    // Função para inserir um cliente
    fun addCliente(cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCliente(cliente)
        }
    }

    // Função para buscar um cliente por ID
    fun getClienteById(id: Int, callback: (Cliente?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val cliente = repository.getClienteById(id)
            callback(cliente)
        }
    }

    // Função para deletar um cliente
    fun deleteCliente(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClienteById(id)
        }
    }

    fun getClienteByEmail(email: String): LiveData<Cliente> {
        return repository.getClienteByEmail(email)
    }
}