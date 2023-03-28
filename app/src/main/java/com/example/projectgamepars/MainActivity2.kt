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
        var register: Button = findViewById(R.id.btnRegister)
        var exit: Button = findViewById(R.id.btnExit)
        var login: Button = findViewById(R.id.btnLogin)
        var laderboar: Button = findViewById(R.id.btnLaderboard)
        var creditos: Button = findViewById(R.id.btnCredits)
        // Fuction Btn Register
        register.setOnClickListener(View.OnClickListener {
            println("Register Window...")
            startRegister()
        })

        //Function Btn Exit
        exit.setOnClickListener(View.OnClickListener {
            finishAffinity()
        })

        login.setOnClickListener(View.OnClickListener {
            println("Login Window...")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        })
        laderboar.setOnClickListener(){
            goToLaderboard()

        }
        creditos.setOnClickListener(){
            val intent = Intent(this, Credits::class.java)
            startActivity(intent)
        }
    }
    private fun startRegister() {
        // Go to the scene of game
        val startScene = Intent(this, Register::class.java)
        startActivity(startScene)
    }
    private fun goToLaderboard(){
        val startScene = Intent(this, Laderboard::class.java)
        startActivity(startScene)
    }
}