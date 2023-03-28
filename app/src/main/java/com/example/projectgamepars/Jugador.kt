package com.example.recyclerview1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectgamepars.R

data class Jugador (val nom_jugador:String, val puntuacio:Int, val foto:String):AppCompatActivity()


{

    /*
    val jugadors = listOf<Jugador>(
        Jugador("Pepe",12,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png"),
        Jugador("Juanito",102,"https://www.kasandbox.org/programming-images/avatars/leafers-seed.png"),
        Jugador("Jaimito",18,"https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png"),
        Jugador("Jorgito",98,"https://www.kasandbox.org/programming-images/avatars/leaf-blue.png")

    )*/
    //val jugador:Jugador=Jugador("Pepe",2, "foto1.jpg")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laderboard)
    }




}





