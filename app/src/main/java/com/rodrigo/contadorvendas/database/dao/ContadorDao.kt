package com.rodrigo.contadorvendas.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rodrigo.contadorvendas.database.dao.model.Contador

@Dao
interface ContadorDao {
    @Query("SELECT * FROM Contador")
    fun buscaContador() : List<Contador>

    @Insert
    fun novoContador(contador: Contador)

    @Update
    fun atualizaContador(contador: Contador)
}