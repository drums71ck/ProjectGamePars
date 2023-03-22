package com.example.projectgamepars

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profile : Activity(){
    var txtusername : TextView? = null
    var userid : TextView? = null
    var txtEmail: TextView? = null
    var txtPuntuacion: TextView? = null
    var squareUsername: TextView? = null
    lateinit var  btnBackArrow: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        main()
    }

    private fun main() {
        userDat()
        cargarTxt()
        cargarBtn()
    }

    private fun cargarBtn() {
        btnBackArrow = findViewById(R.id.btnBack)

        btnBackArrow.setOnClickListener(){
            finish()
        }

    }

    private fun userDat() {
        val auth = FirebaseAuth.getInstance()
        val USERUID = auth.uid!!
        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://mymemory-f114e-default-rtdb.europe-west1.firebasedatabase.app")
        val userRef = database.getReference("DATABASE PLAYERS")
        val email = Login.usEMail
        val query = userRef.orderByChild("Email").equalTo(email)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val userKey = userSnapshot.key
                    // Realizar la actualización de la puntuación del usuario

                    val stats=
                        dataSnapshot.child(USERUID).child("Puntuacio").value.toString()
                    val username = dataSnapshot.child(USERUID).child("Nom").value.toString()
                    userid!!.text=USERUID
                    txtusername!!.text=username
                    txtEmail!!.text=email
                    txtPuntuacion!!.text=stats
                    squareUsername!!.text=username



                    }



                }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        }


    // cargara el texto
    private fun cargarTxt() {
        txtusername = findViewById(R.id.txtName)
        userid = findViewById(R.id.txtUserId)
        txtEmail =  findViewById(R.id.user_email)
        txtPuntuacion = findViewById(R.id.maxScore)
        squareUsername = findViewById(R.id.profile_user)

    }



}