package com.example.validacao

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.validacao.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
// resgatando o conteuto do objeto do design usando o objeto kotlin
        val btVerifica: Button = findViewById(R.id.btnValidar)
        btVerifica.setOnClickListener {
            validar()
        }
        val btCalcular: Button = findViewById(R.id.btnCalcular)
        btCalcular.setOnClickListener{
            calcular()
        }
    }

    // Função que recebe os dados e verifica a integridade dos mesmos.
    @RequiresApi(Build.VERSION_CODES.O)
    private fun validar() {
// Igualando Objetos do Design (XML) com a parte lógica (Kotlin)
        val nome: EditText = findViewById(R.id.edtNome)
        val dtNascimento: EditText = findViewById(R.id.edtNascimento)
        val salario: EditText = findViewById(R.id.edtSalario)
// Guardando valor da propriedade TEXT em Variaveis locais
        val N = nome.text.toString()
        val D = dtNascimento.text.toString()
        val S = salario.text.toString()
// Testa valor recebido na variável N
        if (N.isEmpty()) {
            Toast.makeText(this, "Nome Inválido", Toast.LENGTH_SHORT).show()
            return
        }
// Validar data de nascimento---------------------------------------------------------------
        if (D.isEmpty()) {
            Toast.makeText(
                this, "A Data de Nascimento é obrigatória!", Toast.LENGTH_SHORT).show()
            return
        }
        if (!D.isEmpty()) {
            try {
                var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                var adata = LocalDate.parse(D, formato)
            } catch (e1: Exception) {
// this means it is not double
                Toast.makeText(this, "Data $D Inválida!", Toast.LENGTH_SHORT).show()
                return
            }
        }
// Validar salario -------------------------------------------------------------------------
        if (S.isEmpty()) {
            Toast.makeText(this, "O Salário deve ser informado!", Toast.LENGTH_SHORT).show()
            return
        }
        val valor: Double
        if (!S.isEmpty()) {
            try {
                valor = S.toDouble()
            } catch (e1: Exception) {
// this means it is not double
                Toast.makeText(this, "Salário Inválido!", Toast.LENGTH_SHORT).show()
                return
            }
        }
//------------------------------------------------------------------------------------------
    }

    private fun calcular(){

        val salario: EditText = findViewById(R.id.edtSalario)
        val percentual : EditText = findViewById(R.id.edtPercentual)

        val S = salario.text.toString()
        if (S.isEmpty()){
            Toast.makeText(this, "O Salário deve ser informado!", Toast.LENGTH_SHORT).show()
            return
        }

        val P = percentual.text.toString()

        if (P.isEmpty()){
            Toast.makeText(this, "Insira um Percentual Válido!", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val formato = P.toDouble()
            val formatoS = S.toDouble()

            if (formato in 0.0..100.0) {
                val NS = formatoS + (formatoS * (formato / 100))

                binding.txtNsalario.text = NS.toString()
            } else {
                Toast.makeText(this, "Percentual deve estar entre 0 e 100", Toast.LENGTH_SHORT).show()
                return
            }

        }catch (e1 : Exception){
            Toast.makeText(this, "Erro ao calcular, verifique os valores inseridos!", Toast.LENGTH_SHORT).show()
            return
        }
    }
}