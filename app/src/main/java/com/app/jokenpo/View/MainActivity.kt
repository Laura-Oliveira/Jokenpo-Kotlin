package com.app.jokenpo.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.jokenpo.Model.Computer
import com.app.jokenpo.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{

    private lateinit var txtPlayerName:TextView
    private lateinit var playerName: String
    private lateinit var txtComputerName:TextView
    private lateinit var computerName: String
    private lateinit var resultTextView: TextView
    private var playerScore = 0
    private var computerScore = 0
    private lateinit var jsonPlaceholder: APIInterface

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPlayerName = findViewById(R.id.txt_playerName)
        val playerNameExtra = intent.getStringExtra("player")
        txtPlayerName.text = playerNameExtra

        playerName = intent.getStringExtra("player") ?: "Player"
      //  computerName = "Computer"
        txtComputerName = findViewById(R.id.txt_computerName)
        computerName = txtComputerName.toString()

        resultTextView = findViewById(R.id.txt_result)

        val rockButton = findViewById<Button>(R.id.rockButton)
        val paperButton = findViewById<Button>(R.id.paperButton)
        val scissorsButton = findViewById<Button>(R.id.scissorsButton)
        val btnRanking = findViewById<Button>(R.id.btn_ranking)

        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
        btnRanking.setOnClickListener { showRanking() }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.toys/api/medieval_name/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceholder = retrofit.create(APIInterface::class.java)
        getComputerName()
    }

    private fun playGame(playerChoice: String) {
        val computerChoice = listOf("rock", "paper", "scissors").random()
        val result = determineWinner(playerChoice, computerChoice)

        val playerChoiceText = when (playerChoice) {
            "rock" -> "Rock"
            "paper" -> "Paper"
            "scissors" -> "Scissor"
            else -> ""
        }

        val computerChoiceText = when (computerChoice) {
            "rock" -> "Rock"
            "paper" -> "Paper"
            "scissors" -> "Scissor"
            else -> ""
        }

        val resultText = when (result) {
            0 -> "It's a tie!"
            1 -> "$playerName wins with $playerChoiceText over $computerName's $computerChoiceText."
            -1 -> "$computerName wins with $computerName's $computerChoiceText over $playerChoiceText."
            else -> ""
        }

        // Atualizar pontuação e exibir na interface do usuário
        if (result == 1)
        { playerScore++ }
        else if (result == -1)
        { computerScore++ }
        resultTextView.text = "$resultText\n${resultTextView.text}"
    }

    private fun determineWinner(playerChoice: String, computerChoice: String): Int {
        if (playerChoice == computerChoice) return 0

        return when (playerChoice) {
            "rock" -> if (computerChoice == "scissors") 1 else -1
            "paper" -> if (computerChoice == "rock") 1 else -1
            "scissors" -> if (computerChoice == "paper") 1 else -1
            else -> 0
        }
    }

    private fun updateScoreView() {
        val scoreTextView = findViewById<TextView>(R.id.txt_score)
        scoreTextView.text = "Score: $playerScore - $computerScore"
    }

    private fun showRanking()
    {
        val intent = Intent(this, RankingActivity::class.java)
        intent.putExtra("playerScore", playerScore)
        intent.putExtra("computerScore", computerScore)
        startActivity(intent)
    }

    fun getComputerName()
    {
        val call: Call<Computer> = jsonPlaceholder.getComputer()

        call.enqueue(object : Callback<Computer>
        {
            override fun onResponse(call: Call<Computer>, response: Response<Computer>) {
                if (response.isSuccessful)
                {
                    val computer = response.body()
                    if(computer != null)
                    {
                        val computerNameResponse = computer.name ?: "Nome do Computador não encontrado"
                        Toast.makeText(this@MainActivity, "Nome do Computador: $computerNameResponse", Toast.LENGTH_SHORT).show()
                        Log.d("Nome do Computador", computerNameResponse)
                        Log.d("Response Code", response.code().toString())
                    }
                }
            }

            override fun onFailure(call: Call<Computer>, t: Throwable)
            {
                t.message?.let { Log.d("Erro ao recuperar dados", it) }
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}