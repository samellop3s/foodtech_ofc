package com.example.foodtech.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodtech.entities.Cliente

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Query("SELECT * FROM cliente")
    fun readAllData(): LiveData<List<Cliente>>

    @Query("SELECT * FROM cliente WHERE email = :email LIMIT 1")
    fun getClienteByEmail(email: String): LiveData<Cliente>



    @Query("SELECT * FROM cliente WHERE id_cliente = :id")
    suspend fun getClienteById(id: Int): Cliente?

    @Query("DELETE FROM cliente WHERE id_cliente = :id")
    suspend fun deleteClienteById(id: Int)
}