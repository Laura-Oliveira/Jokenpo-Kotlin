package com.app.jokenpo.View

import com.app.jokenpo.R

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RankingActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        scoreTextView = findViewById(R.id.txt_score)
        backButton = findViewById(R.id.backButton)

        // Obtém a pontuação do jogo da atividade anterior
        val playerScore = intent.getIntExtra("playerScore", 0)
        val computerScore = intent.getIntExtra("computerScore", 0)

        // Exibe a pontuação na interface do usuário
        scoreTextView.text = "Player: $playerScore - Computer: $computerScore"

        // Configura um ouvinte de clique para o botão "Back to Game"
        backButton.setOnClickListener {
            finish() // Fecha a atividade de ranking e volta para o jogo
        }
    }
}

