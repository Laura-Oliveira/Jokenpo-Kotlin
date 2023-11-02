package com.app.jokenpo.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.jokenpo.R
import java.util.*

class MainActivity : AppCompatActivity()
{

    //val playerName = getIntent().getStringExtra("player")
    var txtPlayerName:TextView? = null
    var playerName:String? = null
  //  val objIntent:Intent = intent


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPlayerName = findViewById(R.id.txt_playerName)
        val playerNameExtra = intent.getStringExtra("player")
        txtPlayerName!!.text = playerNameExtra
    }

    fun playGame(view: View)
    {
        val playerChoice = view.tag.toString()
        val computerChoice = generateComputerChoice()

        val result = determineWinner(playerChoice, computerChoice)
       // displayResult(playerChoice, computerChoice, result)
    }

    private fun generateComputerChoice(): String {
        val random = Random().nextInt(3)
        return when (random) {
            0 -> "rock"
            1 -> "paper"
            else -> "scissors"
        }
    }

    private fun determineWinner(playerChoice: String, computerChoice: String): String {
        return when {
            playerChoice == computerChoice -> "It's a tie!"
            playerChoice == "rock" && computerChoice == "scissors" ||
                    playerChoice == "paper" && computerChoice == "rock" ||
                    playerChoice == "scissors" && computerChoice == "paper" -> "You win!"
            else -> "Computer wins!"
        }
    }

  /*  private fun displayResult(playerChoice: String, computerChoice: String, result: String)
    {
        playerChoiceTextView.text = "Your choice: $playerChoice"
        computerChoiceTextView.text = "Computer's choice: $computerChoice"
        resultTextView.text = result
    } */
}
