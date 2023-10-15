package com.example.segundoparcial

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.segundoparcial.databinding.ActivityMainBinding

class ActivityInquilinos : AppCompatActivity() {


    lateinit var codigo: EditText
    lateinit var nombre: EditText
    lateinit var apellido: EditText
    lateinit var telefono: EditText
    lateinit var email: EditText
    lateinit var mensaje: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquilinos)
        //val  binding2 = ActivityMainBinding.inflate(layoutInflater)
        codigo = findViewById(R.id.etCodigoInquilino)
        nombre = findViewById(R.id.etNombreInquilino)
        apellido = findViewById(R.id.etApellidoInquilino)
        telefono = findViewById(R.id.etNumTelefonoInquilino)
        email = findViewById(R.id.etEmailInquilino)
        mensaje = findViewById(R.id.tvMensajeInquilino)

        //setContentView(binding2.root)

    }

    fun insertarInquilinoDTO(view: View) {
        if (codigo.text.toString().length > 0 && nombre.text.toString().length > 0 && apellido.text.toString().length > 0 && telefono.text.toString().length > 0 && email.text.toString().length > 0) {
            var inquilino = Inquilinos(
                codigo.text.toString().toInt(),
                nombre.text.toString(),
                apellido.text.toString(),
                telefono.text.toString().toInt(),
                email.text.toString()
            )
            var DB = DataBase(this)
            mensaje.setText(DB.insertarDatosInquilinos(inquilino))
        }
    }

    fun listarInquilinoDTO(view: View) {
        var db = DataBase(this)
        var data = db.listarDatosInquilinos()
        mensaje.text = ""
        for (i in 0..data.size - 1) {
            val inquilino = data.get(i)
            mensaje.append("Id:${inquilino.idInquilino}, Codigo: ${inquilino.codigoInquilino}, Nombre: ${inquilino.nombreInquilino}, Apellido: ${inquilino.apellidoInquilino}, Telefono: ${inquilino.telefonoInquilino}, Correo: ${inquilino.emailInquilino} \n")
            db.close()
        }
    }

    fun actualizarInquilinoDTO(view: View) {
        var db = DataBase(this)
        val res = db.actualizarDatosInquilinos(
            codigo.text.toString(),
            nombre.text.toString(),
            apellido.text.toString(),
            telefono.text.toString().toInt(),
            email.text.toString()
        )

        mensaje.setText(res)

    }

    fun eliminarInquilinoDTO(view: View) {
        var db = DataBase(this)
        val res = db.eliminarDatosInquilinos(codigo.text.toString())
        mensaje.setText(res)
    }


    fun salir(view: View) {
        finish()
    }
}