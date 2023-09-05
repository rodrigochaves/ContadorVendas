package com.rodrigo.contadorvendas.database.dao.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Contador (
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var numPossiveisCompradores: Int = 0,
    var numVendasRealizadas: Int = 0
) : Parcelable {
    fun defineId(novoId: Long) {
        id = novoId
    }
    fun defineNumPossiveisCompradores(novoNumPossiveisCompradores: Int) {
        numPossiveisCompradores = novoNumPossiveisCompradores
    }
    fun defineNumVendasRealizadas(novoVendasRealizadas: Int) {
        numVendasRealizadas = novoVendasRealizadas
    }
}