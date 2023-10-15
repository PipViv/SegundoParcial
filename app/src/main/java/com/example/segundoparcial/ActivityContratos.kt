package com.example.segundoparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityContratos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contratos)
    }
    fun salir(view: View){
        finish()
    }
}