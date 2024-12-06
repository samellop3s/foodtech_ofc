package com.example.foodtech.repository

import androidx.lifecycle.LiveData
import com.example.foodtech.dao.ClienteDao
import com.example.foodtech.entities.Cliente

class ClienteRepository(private val clienteDao: ClienteDao) {

    // LiveData para ler todos os dados de clientes
    val readAllData: LiveData<List<Cliente>> = clienteDao.readAllData()


    // Inserir um novo cliente no banco de dados
    suspend fun insertCliente(cliente: Cliente) {
        clienteDao.insert(cliente)
    }

    // Buscar todos os clientes
    //suspend fun getAllClientes(): List<Cliente> {
    //    return clienteDao.getAllClientes()
    //}

    // Buscar um cliente por ID
    suspend fun getClienteById(id: Int): Cliente? {
        return clienteDao.getClienteById(id)
    }

    // Deletar um cliente por ID
    suspend fun deleteClienteById(id: Int) {
        clienteDao.deleteClienteById(id)
    }

    fun getClienteByEmail(email: String): LiveData<Cliente> {
        return clienteDao.getClienteByEmail(email)
    }
}