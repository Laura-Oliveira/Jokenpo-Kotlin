package com.app.jokenpo.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.app.jokenpo.R
import com.app.jokenpo.View.MainActivity

class PlayerActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        var editTextPlayerName:EditText = findViewById(R.id.editText_playerName)
        var btnPlay:Button = findViewById(R.id.btn_play)

        btnPlay.setOnClickListener {
            var playerName = editTextPlayerName.text.toString()
            Log.d("player antes de entrar na funcao", playerName)
            openGamePage(playerName)
        }
    }

    fun openGamePage(playerNameGame: String?)
    {
        if (!playerNameGame.isNullOrBlank())
        {
            val objIntent = Intent(this, MainActivity::class.java)
            objIntent.putExtra("player", playerNameGame)
            startActivity(objIntent)
            Toast.makeText(this, "Bom Jogo!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Forneca um Nome para Jogar", Toast.LENGTH_SHORT).show();
        }
    }
}