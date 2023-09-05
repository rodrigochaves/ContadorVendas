package com.rodrigo.contadorvendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodrigo.contadorvendas.database.AppDataBase
import com.rodrigo.contadorvendas.database.dao.ContadorDao
import com.rodrigo.contadorvendas.databinding.ActivityMainBinding
import com.rodrigo.contadorvendas.database.dao.model.Contador

class MainActivity : AppCompatActivity() {
    private var idContador = 0L
    private var numPossiveisCompradores = 0
    private var numVendasRealizadas = 0

    lateinit var db: AppDataBase
    lateinit var contadorDao: ContadorDao
    lateinit var contador: Contador

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Contador de Vendas"

        db = AppDataBase.instancia(this)
        contadorDao = db.contadorDao()

        val contadorSalvo = contadorDao.buscaContador()

        if (contadorSalvo.isNotEmpty()) {
            val contadorExistente = contadorSalvo[0]

            idContador = contadorExistente.id
            numPossiveisCompradores = contadorExistente.numPossiveisCompradores
            numVendasRealizadas = contadorExistente.numVendasRealizadas

            contador = contadorExistente

            updatePossiveisCompradores()
            updateVendasRealizadas()
        } else {
            val novoContador = Contador(
                numPossiveisCompradores = numPossiveisCompradores,
                numVendasRealizadas = numVendasRealizadas
            )
            contadorDao.novoContador(novoContador)

            contador = novoContador
        }

        setContentView(binding.root)

        binding.btnPossiveisCompradoresMenos.setOnClickListener {
            if (numPossiveisCompradores > 0) {
                numPossiveisCompradores--
                contador.defineNumPossiveisCompradores(numPossiveisCompradores)
                salvarContatadorNoBancoDeDados()
                updatePossiveisCompradores()
            }
        }

        binding.btnPossiveisCompradoresMais.setOnClickListener {
            numPossiveisCompradores++
            contador.defineNumPossiveisCompradores(numPossiveisCompradores)
            salvarContatadorNoBancoDeDados()
            updatePossiveisCompradores()
        }

        binding.btnVendasRealizadasMenos.setOnClickListener {
            if (numVendasRealizadas > 0) {
                numVendasRealizadas--
                contador.defineNumVendasRealizadas(numVendasRealizadas)
                salvarContatadorNoBancoDeDados()
                updateVendasRealizadas()
            }
        }

        binding.btnVendasRealizadasMais.setOnClickListener {
            numVendasRealizadas++
            contador.defineNumVendasRealizadas(numVendasRealizadas)
            salvarContatadorNoBancoDeDados()
            updateVendasRealizadas()
        }

        binding.btnReset.setOnClickListener {
            numPossiveisCompradores = 0
            numVendasRealizadas = 0
            contador.defineNumPossiveisCompradores(numPossiveisCompradores)
            contador.defineNumVendasRealizadas(numVendasRealizadas)
            salvarContatadorNoBancoDeDados()
            updatePossiveisCompradores()
            updateVendasRealizadas()
        }
    }

    private fun updatePossiveisCompradores() {
        binding.possiveisCompradoresContador.text = "${contador.numPossiveisCompradores}"
    }

    private fun updateVendasRealizadas() {
        binding.vendasRealizadasContador.text = "${contador.numVendasRealizadas}"
    }

    private fun salvarContatadorNoBancoDeDados() {
        contadorDao.atualizaContador(contador)
    }
}