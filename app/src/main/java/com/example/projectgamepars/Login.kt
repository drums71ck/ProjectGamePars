package com.example.projectgamepars

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    lateinit var correoLogin : EditText
    lateinit var  passLogin : EditText
    lateinit var btnLogin : Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        correoLogin = findViewById<EditText>(R.id.correoLogin)
        passLogin = findViewById<EditText>(R.id.passLogin)
        btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener(){
            var email:String = correoLogin.getText().toString()
            var passw:String = passLogin.getText().toString()
            //Validacion

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoLogin.setError("Invalid Mail")
            }else if (passw.length<6){
                passLogin.setError("password less tan six characters")

            }else{
              //log
                LoginDeJugador(email, passw)
            }

        }
    }

    private fun LoginDeJugador(email: String, passw: String) {
        auth.signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        val intent= Intent(this, Game::class.java)
        startActivity(intent)
        print("aqiu estas")
        finish()
    }
}