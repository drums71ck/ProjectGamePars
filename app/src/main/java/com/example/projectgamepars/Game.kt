package com.example.projectgamepars

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import  androidx.annotation.Nullable
import  android.os.Handler
import android.widget.ImageView
import java.util.Arrays
import java.util.Collections

class Game : Activity(){
    // Variables of Carts
    lateinit var  imb00: ImageButton
    lateinit var  imb01: ImageButton
    lateinit var  imb02: ImageButton
    lateinit var  imb03: ImageButton
    lateinit var  imb04: ImageButton
    lateinit var  imb05: ImageButton
    lateinit var  imb06: ImageButton
    lateinit var  imb07: ImageButton
    // Tablero
    val tablero = arrayOfNulls<ImageButton>(8)
    // Botones
    lateinit var btnReiniciar : Button
    lateinit var btnSalir : Button
    // Text
    lateinit var txtPuntuacion : TextView
    var puntuacion: Int = 0
    var aciertos:Int = 0

    //Imagenes
    var imagenes = IntArray(8)
    var fondo: Int = 0
    var arrayDesordenado = ArrayList<Int>()
    lateinit var primero:ImageButton
    val numeroPrimero = Int
    val numeroSegundo = Int
    val bloqueo : Boolean = false
    final val  handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        init()
    }

    fun cargarTablero(){
        imb00 = findViewById(R.id.boton00)
        imb01 = findViewById(R.id.boton01)
        imb02 = findViewById(R.id.boton02)
        imb03 = findViewById(R.id.boton03)
        imb04 = findViewById(R.id.boton04)
        imb05 = findViewById(R.id.boton05)
        imb06 = findViewById(R.id.boton06)
        imb07 = findViewById(R.id.boton07)

        tablero[0] = imb00
        tablero[1] = imb01
        tablero[2] = imb02
        tablero[3] = imb03
        tablero[4] = imb04
        tablero[5] = imb05
        tablero[6] = imb06
        tablero[7] = imb07


    }

    fun cargarBtn(){
        btnReiniciar = findViewById(R.id.btnJuegoReiniciar)
        btnSalir = findViewById(R.id.btnJuegoSalir)

        btnReiniciar.setOnClickListener(){
            init()
        }

        btnSalir.setOnClickListener(){
            finish()
        }
    }

    fun cargarTxt(){
        txtPuntuacion = findViewById(R.id.txt_puntuacion)
        txtPuntuacion.setText("Puntacion: "+puntuacion)
    }

    fun cargarImagenes(){
        imagenes = intArrayOf(
            R.drawable.la0,
            R.drawable.la1,
            R.drawable.la2,
            R.drawable.la3,
            R.drawable.la4,
            R.drawable.la5,
            R.drawable.la6,
            R.drawable.la7
        )
        fondo = R.drawable.fondo
    }


    private fun barajar(longitud: Int): ArrayList<Int> {
        var result = ArrayList<Int>()
        for (i in 0 until longitud*2){ //bajara el array y añadira la nueva array como la principal
            result.add(i % longitud)
            println(result)

        }
        Collections.shuffle(result)
        println(Arrays.toString(result.toArray()))
        return result

    }

    fun init(){
        cargarTablero()
        cargarBtn()
        cargarTxt()
        cargarImagenes()
        arrayDesordenado = barajar(imagenes.size)
        for (i in 0 until tablero.size){
            tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tablero[i]!!.setImageResource(imagenes[arrayDesordenado.get(i)])
        }
    }

}
