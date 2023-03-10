package com.example.projectgamepars

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.core.os.postDelayed
import java.util.Arrays
import java.util.Collections


class Game2 : Activity(){
    // Variables of Carts
    lateinit var  imb00: ImageButton
    lateinit var  imb01: ImageButton
    lateinit var  imb02: ImageButton
    lateinit var  imb03: ImageButton
    lateinit var  imb04: ImageButton
    lateinit var  imb05: ImageButton
    lateinit var  imb06: ImageButton
    lateinit var  imb07: ImageButton
    lateinit var  imb08: ImageButton
    lateinit var  imb09: ImageButton
    lateinit var  imb10: ImageButton
    lateinit var  imb11: ImageButton
    // Tablero
    val tablero = arrayOfNulls<ImageButton>(12)
    // Botones
    lateinit var btnReiniciar : Button
    lateinit var btnSalir : Button
    // Text
    lateinit var txtPuntuacion : TextView
    var puntuacion: Int = 0
    var aciertos:Int = 0

    //Imagenes
    var imagenes = IntArray(12)
    var fondo: Int = 0
    var arrayDesordenado = ArrayList<Int>()

    var numeroPrimero : Int? = null
    var numeroSegundo : Int? = null
    var bloqueo : Boolean = false
    var primero: ImageButton? = null
    final val  handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)
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
        imb08 = findViewById(R.id.boton08)
        imb09 = findViewById(R.id.boton09)
        imb10 = findViewById(R.id.boton10)
        imb11 = findViewById(R.id.boton11)

        tablero[0] = imb00
        tablero[1] = imb01
        tablero[2] = imb02
        tablero[3] = imb03
        tablero[4] = imb04
        tablero[5] = imb05
        tablero[6] = imb06
        tablero[7] = imb07
        tablero[8] = imb08
        tablero[9] = imb09
        tablero[10] = imb10
        tablero[11] = imb11


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
            /*
            R.drawable.la6,
            R.drawable.la7*/
        )
        fondo = R.drawable.fondo
    }


    private fun barajar(longitud: Int): ArrayList<Int> {
        var result = ArrayList<Int>()
        for (i in 0 until longitud*2){ //bajara el array y añadira la nueva array como la principal
            result.add(i % longitud)

        }
        println(result)
        Collections.shuffle(result)
        println(Arrays.toString(result.toArray()))
        return result

    }
    private fun comprobar(i: Int, imgb: ImageButton) {
        if (primero == null) {
            primero = imgb
            primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
            primero!!.setImageResource(imagenes[arrayDesordenado[i]])
            primero!!.isEnabled = false
            numeroPrimero = arrayDesordenado[i]
        } else {
            bloqueo = true
            imgb.scaleType = ImageView.ScaleType.CENTER_CROP
            imgb.setImageResource(imagenes[arrayDesordenado[i]])
            imgb.isEnabled = false
            numeroSegundo = arrayDesordenado[i]
            if (numeroPrimero == numeroSegundo) {
                primero = null
                bloqueo = false
                aciertos++
                puntuacion++
                txtPuntuacion.text = "Puntuación: "+puntuacion
                if (aciertos == imagenes.size) {
                    val toast = Toast.makeText(applicationContext, "¡Has ganado!", Toast.LENGTH_LONG)
                    toast.show()
                }
            } else {
                handler.postDelayed({
                    primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    primero!!.setImageResource(fondo)
                    primero!!.isEnabled = true
                    imgb.scaleType = ImageView.ScaleType.CENTER_CROP
                    imgb.setImageResource(fondo)
                    imgb.isEnabled = true
                    bloqueo = false
                    primero = null
                    puntuacion--
                    txtPuntuacion.text = "Puntuación: "+puntuacion
                }, 1000)
            }
        }
    }


    fun init(){
        cargarTablero()
        cargarBtn()
        cargarTxt()
        cargarImagenes()
        arrayDesordenado = barajar(imagenes.size)
        // Asign the image from array
        for (i in 0 until tablero.size){
            tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tablero[i]!!.setImageResource(imagenes[arrayDesordenado.get(i)])
        }
        // Time to show the carts
        Handler().postDelayed({
            for (i in 0 until tablero.size){
                tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                tablero[i]!!.setImageResource(fondo)
            }
        }, 1000)
        for (i in 0 until tablero.size){
            tablero[i]!!.isEnabled = true
            tablero[i]!!.setOnClickListener(){
                if(!bloqueo){
                    comprobar(i, tablero[i]!!)
                }
            }
        }

    }

}