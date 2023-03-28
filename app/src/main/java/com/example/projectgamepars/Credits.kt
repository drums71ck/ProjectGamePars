package com.example.projectgamepars

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import java.util.*

class Credits : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        var mp = MediaPlayer.create(this, R.raw.capi);

        mp.start()

        var returnButton: Button = findViewById(R.id.btnReturn)

        supportFragmentManager.commit {
            replace<SegonFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        timer.scheduleAtFixedRate(TimeTask(),0L,3000L)

        returnButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            timer.cancel()
            mp.stop()
            startActivity(intent)
        })
    }



    private inner class TimeTask:TimerTask(){
        private var numeroFragment:Int=0;
        override fun run() {
            numeroFragment++
            if (numeroFragment>2) numeroFragment=1
            if (numeroFragment==1) {
                supportFragmentManager.commit {
                    replace<SegonFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }
            else {
                supportFragmentManager.commit {
                    replace<PrimerFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }
        }
    }



}