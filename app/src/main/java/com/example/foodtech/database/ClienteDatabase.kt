package com.example.foodtech.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodtech.dao.ClienteDao
import com.example.foodtech.entities.Cliente
import android.content.Context

@Database(entities = [Cliente::class], version = 1, exportSchema = false)
abstract class ClienteDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao

    companion object {
        @Volatile
        private var INSTANCE: ClienteDatabase? = null

        fun getDatabase(context: Context): ClienteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClienteDatabase::class.java,
                    "cliente_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}