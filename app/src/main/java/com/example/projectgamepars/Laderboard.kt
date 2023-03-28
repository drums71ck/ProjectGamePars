package com.example.projectgamepars

import android.content.ContentValues.TAG
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.Insets.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectgamepars.adapter.JugadorsAdapter
import com.example.recyclerview1.Jugador
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Laderboard : AppCompatActivity() {


    fun datos() {
        val database =
            FirebaseDatabase.getInstance("https://mymemory-f114e-default-rtdb.europe-west1.firebasedatabase.app")
        val usersRef = database.getReference("DATABASE PLAYERS")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Aquí se ejecuta el código cada vez que cambia la información de los usuarios en la base de datos

                // Iterar a través de los usuarios y mostrar su información
                for (userSnapshot in snapshot.children) {
                    val userId = userSnapshot.key // Obtener el ID del usuario
                    val email = userSnapshot.child("Email").getValue(String::class.java) // Obtener el correo electrónico del usuario
                     userName = userSnapshot.child("Nom").getValue(String::class.java) // Obtener el nombre de usuario del usuario
                    val userPunt = userSnapshot.child("Puntuacio").value.toString()
                     userPuntation = userPunt!!.toInt()

                    val user = Jugador(userName!!,userPuntation,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png")

                    // Hacer algo con los valores obtenidos, como imprimirlos en la consola
                    //println("Usuario con ID $userId: correo electrónico = $email, nombre de usuario = $userName, $userPunt")
                    jugadors.add(user)
                    println("hay ${jugadors.size}")
                    val userAdapter = JugadorsAdapter(jugadors)
                    val recyclerView=findViewById<RecyclerView>(R.id.RecyclerOne)
                    recyclerView.adapter = userAdapter


                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Aquí se ejecuta el código si se produce un error al obtener los datos de la base de datos
                println("Error al obtener datos de la base de datos: ${error.message}")
            }
            })
    }
    var userName : String? = null
    var userPuntation : Int = 0
    val jugadors = ArrayList<Jugador>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laderboard)
        datos()
        initRecyclerView()


    }

    fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.RecyclerOne)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=JugadorsAdapter(jugadors)
    }
}



