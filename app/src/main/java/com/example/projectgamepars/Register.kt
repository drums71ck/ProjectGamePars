package com.example.projectgamepars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {
    lateinit var correoEt :EditText
    lateinit var passEt: EditText
    lateinit var nicknameEt: EditText
    lateinit var fechaTxt: TextView
    lateinit var Register: Button
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
    }


}