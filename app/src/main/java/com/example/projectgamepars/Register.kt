package com.example.projectgamepars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class Register : AppCompatActivity() {
    lateinit var correoEt :EditText
    lateinit var passEt: EditText
    lateinit var nicknameEt: EditText
    lateinit var fechaTxt: TextView
    lateinit var Register: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        correoEt = findViewById<EditText>(R.id.correoEt)
        passEt = findViewById<EditText>(R.id.passEt)
        nicknameEt = findViewById<EditText>(R.id.nombreEt)
        fechaTxt = findViewById<TextView>(R.id.fechaEt)
        Register = findViewById<Button>(R.id.btnRegister)


        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getInstance()
        val formatedDate = formatter.format(date)

        fechaTxt.setText(formatedDate)
        auth = FirebaseAuth.getInstance()

        Register.setOnClickListener(){
            var email:String = correoEt.getText().toString()
            var pass:String = passEt.getText().toString()

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoEt.setError("Invalid Mail")
            }else if(pass.length<6){
                passEt.setError("Password less than 6 characters")
            }else{
                RegisterPlayer(email, pass)
            }




        }



    }



    private fun RegisterPlayer(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"createUserWithEmail:success",Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

            }

    }
    fun updateUI(user:FirebaseUser?){
        if(user!=null){
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = correoEt.getText().toString()
            var passString: String = passEt.getText().toString()
            var nombreString: String = nicknameEt.getText().toString()
            var fechaString: String = fechaTxt.getText().toString()
            var dadesJugador : HashMap<String, String> = HashMap<String,String>()
            dadesJugador.put ("Uid",uidString)
            dadesJugador.put ("Email",correoString)
            dadesJugador.put ("Password",passString)
            dadesJugador.put ("Nom",nombreString)
            dadesJugador.put ("Data",fechaString)
            dadesJugador.put ("Puntuacio", puntuacio.toString())
            var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://mymemory-f114e-default-rtdb.europe-west1.firebasedatabase.app/")
            var reference: DatabaseReference = database.getReference("DATABASE PLAYERS")
            if(reference != null){
                reference.child(uidString).setValue(dadesJugador)
                Toast.makeText(this, "User Registered",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "ErrorBD", Toast.LENGTH_SHORT).show()
            }
            finish()

        }else{
            Toast.makeText(this,"error create user :(",Toast.LENGTH_SHORT).show()
        }


    }


}