package com.example.foodtech.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class Cliente(
    @PrimaryKey (autoGenerate = true)
    val id_cliente: Int = 0,
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String
)