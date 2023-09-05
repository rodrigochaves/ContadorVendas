package com.rodrigo.contadorvendas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigo.contadorvendas.database.dao.ContadorDao
import com.rodrigo.contadorvendas.database.dao.model.Contador

@Database(entities = [Contador::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun contadorDao() : ContadorDao

    companion object {
        fun instancia(context: Context) : AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "contador.db"
            ).allowMainThreadQueries().build()
        }
    }
}