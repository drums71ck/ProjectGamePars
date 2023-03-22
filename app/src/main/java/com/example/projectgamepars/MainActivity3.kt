package com.example.projectgamepars

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var play: Button = findViewById(R.id.btnPlay)
        var profile: Button = findViewById(R.id.btnProfile)
        var exit: Button = findViewById(R.id.btnExit)

        play.setOnClickListener(View.OnClickListener {
            println("Login Window...")
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        })

        profile.setOnClickListener(View.OnClickListener {
            println("Profile Window...")
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        })

        exit.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}