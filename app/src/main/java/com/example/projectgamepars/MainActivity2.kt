package com.example.projectgamepars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var play: Button = findViewById(R.id.btnPlay)
        var exit: Button = findViewById(R.id.btnExit)

        // Fuction Btn play
        play.setOnClickListener(View.OnClickListener {
            println("Iniciando juego...")
            startGame()
        })

        //Function Btn Exit
        exit.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
    private fun startGame() {
        // Go to the scene of game
        val startScene = Intent(this, Game::class.java)
        startActivity(startScene)
    }
}