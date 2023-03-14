package com.example.projectgamepars

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

class Splash() : AppCompatActivity() {

    val DURATION: Long = 4500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        playMusic()
        val fondoApp = findViewById<ImageView>(R.id.my_gif_view)



        Glide.with(this).load(R.drawable.memory).into(fondoApp)

        cambiarActivity()



    }
    override fun onRestart() {
        super.onRestart()
        playMusic()

    }

    private fun playMusic() {
        val mp = MediaPlayer.create(this, R.raw.music)
        mp.start()

    }

    private fun cambiarActivity(){
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }, DURATION)
    }

}