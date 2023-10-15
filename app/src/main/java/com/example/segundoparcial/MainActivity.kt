package com.example.segundoparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.segundoparcial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun btnVentanaInquilinos(view: View){
        val opcionInquilinos = Intent(this, ActivityInquilinos::class.java)

        startActivity(opcionInquilinos)
    }

    fun btnVentanaInmuebles(view: View){
        val opcionInmuebles = Intent(this, ActivityInMuebles::class.java)

        startActivity(opcionInmuebles)
    }

    fun btnVentanaContratos(view: View){
        val opcionContratos = Intent(this, ActivityContratos::class.java)

        startActivity(opcionContratos)
    }

}