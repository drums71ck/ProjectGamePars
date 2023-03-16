package com.example.projectgamepars

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Laderboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laderboard)
        cargarDatos()
    }

    private fun cargarDatos() {
        // Obtenemos la instancio pegando la url de
        val database = FirebaseDatabase.getInstance("https://mymemory-f114e-default-rtdb.europe-west1.firebasedatabase.app")
        val usersRef = database.getReference("DATABASE PLAYERS")

        usersRef.get().addOnSuccessListener { dataSnapshot ->
            for (childSnapshot in dataSnapshot.children) {
                val userId = childSnapshot.key.toString()
                val userName = childSnapshot.child("Nom").value.toString()
                val userEmail = childSnapshot.child("Email").value.toString()
                val userPunt = childSnapshot.child("Puntuacio").value.toString()
                // Obténemos los datos y lo printeamos
                Log.d(TAG, "User $userId - $userName, $userEmail,$userPunt")

            }
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error getting users: $exception")
        }

    }
}
