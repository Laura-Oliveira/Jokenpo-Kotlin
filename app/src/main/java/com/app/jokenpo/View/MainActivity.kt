package com.app.jokenpo.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.jokenpo.R
import android.view.View
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
