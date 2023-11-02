package com.app.jokenpo.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.jokenpo.R
import java.util.*

class MainActivity : AppCompatActivity()
{

    private lateinit var txtPlayerName:TextView
    private lateinit var playerName: String
    private lateinit var computerName: String

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPlayerName = findViewById(R.id.txt_playerName)
        val playerNameExtra = intent.getStringExtra("player")
        txtPlayerName!!.text = playerNameExtra

        playerName = intent.getStringExtra("player") ?: "Player"
        computerName = "Computer"

        resultTextView = findViewById(R.id.resultTextView)
        resultTextView.text = "Resultados"

        val rockButton = findViewById<Button>(R.id.rockButton)
        val paperButton = findViewById<Button>(R.id.paperButton)
        val scissorsButton = findViewById<Button>(R.id.scissorsButton)

        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
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
}