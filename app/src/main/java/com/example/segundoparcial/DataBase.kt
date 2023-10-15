package com.example.segundoparcial

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var DB = "DbCasas"

class DataBase(context: Context) : SQLiteOpenHelper(context, DB, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {

        val CREATE_TABLE_INQUILINO = "CREATE TABLE inquilinos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo INTEGER(10)," +
                "apellidos VARCHAR(50)," +
                "nombre VARCHAR(50)," +
                "celular INTEGER(11)," +
                "correo VARCHAR(60))";
        p0?.execSQL(CREATE_TABLE_INQUILINO);

        val CREATE_TABLE_INMUEBLE = "CREATE TABLE inmuebles(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR(50)," +
                "tipo VARCHAR(10)," +
                "direccion VARCHAR(100)," +
                "foto BLOB" +
                ")";
        p0?.execSQL(CREATE_TABLE_INMUEBLE);

        val CREATE_TABLE_CONTRATO = "CREATE TABLE contrato(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_inquilino INTEGER," +
                "id_inmueble INTEGER," +
                "fecha_inicial VARCHAR(30)," +
                "fecha_final VARCHAR(30)," +
                "observaciones VARCHAR(300)," +
                "FOREIGN KEY (id_inquilino) REFERENCES inquilinos(id)," +
                "FOREIGN KEY (id_inmueble) REFERENCES inmuebles(id))";

        p0?.execSQL(CREATE_TABLE_CONTRATO);

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertarDatosInmuebles(inmuebles: InMuebles): String {
        val db = this.writableDatabase;
        var contenedor = ContentValues();

        contenedor.put("nombre", inmuebles.nombreInMueble);
        contenedor.put("tipo", inmuebles.tipoInMueble);
        contenedor.put("direccion", inmuebles.direccionInMueble);
        contenedor.put("foto", inmuebles.foto)

        var resultado = db.insert("inmuebles", null, contenedor)

        if (resultado == -1.toLong()) {
            return "Error al Guardar"
        } else {
            return "Datos Guardados... (OK)"
        }

        db.close()

    }

    fun listarDatosInMuebles(inMuebles: InMuebles): MutableList<InMuebles>{
        var lista: MutableList<InMuebles> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM inmuebles"
        var resultado = db.rawQuery(sql, null)

        if(resultado.moveToFirst()){
            do{
                var inmueble = InMuebles()

                inmueble.idInMueble = resultado.getString(0).toInt()
                inmueble.nombreInMueble =
                    resultado.getString(resultado.getColumnIndex("nombre").toInt())
                inmueble.tipoInMueble = resultado.getString(resultado.getColumnIndex("tipo").toInt())
                inmueble.direccionInMueble = resultado.getString(resultado.getColumnIndex("direccion").toInt())
                inmueble.foto = resultado.getBlob(resultado.getColumnIndex("foto").toInt())

                lista.add(inmueble)
            } while (resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }

    fun eliminarDatosInMueble(id: String): String {
        if (id.length > 0) {
            val db = writableDatabase
            var resultado = db.delete("inmuebles", "id=?", arrayOf(id))

            if (resultado == 0) {
                return "No se pudo Eliminar Los Datos"
            } else {
                return "Datos Eliminados... (OK)"
            }

            db.close()
        } else {
            return "No se pudo Eliminar Los Datos"
        }
    }

    fun actualizarDatosInMueble(id: String, nombre: String, tipo: String, direccion: String, foto:ByteArray): String{
        val db = this.writableDatabase
        val contenedor = ContentValues()

        contenedor.put("id", id)
        contenedor.put("nombre", nombre)
        contenedor.put("tipo", tipo)
        contenedor.put("direccion", direccion)
        contenedor.put("foto", foto)

        var resultado = db.update("inmuebles", contenedor, "id=?", arrayOf(id))

        if (resultado == 0) {
            return "No se puden Actualizar los Datos"
        } else {
            return "Datos Actualizados (OK)"
        }
    }

    fun insertarDatosInquilinos(inquilinos: Inquilinos): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()

        contenedor.put("codigo", inquilinos.codigoInquilino)
        contenedor.put("nombre", inquilinos.nombreInquilino)
        contenedor.put("apellidos", inquilinos.apellidoInquilino)
        contenedor.put("celular", inquilinos.telefonoInquilino)
        contenedor.put("correo", inquilinos.emailInquilino)

        var resultado = db.insert("Inquilinos", null, contenedor)

        if (resultado == -1.toLong()) {
            return "Error al Guardar"
        } else {
            return "Datos Guardados... (OK)"
        }

        db.close()
    }

    fun listarDatosInquilinos(): MutableList<Inquilinos> {
        var lista: MutableList<Inquilinos> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Inquilinos"
        var resultado = db.rawQuery(sql, null)

        if (resultado.moveToFirst()) {
            do {
                var inquilino = Inquilinos()

                inquilino.idInquilino = resultado.getString(0).toInt()
                inquilino.codigoInquilino =
                    resultado.getString(resultado.getColumnIndex("codigo").toInt()).toInt()
                inquilino.nombreInquilino =
                    resultado.getString(resultado.getColumnIndex("nombre").toInt())
                inquilino.apellidoInquilino =
                    resultado.getString(resultado.getColumnIndex("apellidos").toInt())
                inquilino.telefonoInquilino =
                    resultado.getString(resultado.getColumnIndex("celular").toInt())
                        .toInt()
                inquilino.emailInquilino =
                    resultado.getString(resultado.getColumnIndex("correo").toInt())

                lista.add(inquilino)
            } while (resultado.moveToNext())
        }

        resultado.close()
        db.close()
        return lista
    }

    fun eliminarDatosInquilinos(codigo: String): String {
        if (codigo.length > 0) {
            val db = writableDatabase
            var resultado = db.delete("inquilinos", "codigo=?", arrayOf(codigo))

            if (resultado == 0) {
                return "No se pudo Eliminar Los Datos"
            } else {
                return "Datos Eliminados... (OK)"
            }

            db.close()
        } else {
            return "No se pudo Eliminar Los Datos"
        }
    }

    fun actualizarDatosInquilinos(
        codigo:String,
        nombre: String,
        apellido: String,
        telefono: Int,
        email: String
    ): String {
        val db=this.writableDatabase
        val contenedor = ContentValues()
        contenedor.put("codigo", codigo)
        contenedor.put("nombre", nombre)
        contenedor.put("apellidos", apellido)
        contenedor.put("celular", telefono)
        contenedor.put("correo", email)

        var resultado = db.update("inquilinos", contenedor, "codigo=?", arrayOf(codigo))


        if (resultado == 0) {
            return "No se puden Actualizar los Datos"
        } else {
            return "Datos Actualizados (OK)"
        }


    }

    fun insertarContrato(contratos: Contratos): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()

        contenedor.put("id_inquilino", contratos.idInquilino)
        contenedor.put("id_inmueble", contratos.idInMueble)
        contenedor.put("fecha_inicial", contratos.fechaInicial)
        contenedor.put("fecha_final", contratos.fechaFinal)
        contenedor.put("observaciones", contratos.observacion)

        val resultado = db.insert("contratos", null, contenedor)


        return if (resultado == -1.toLong()) {
            "Error al Guardar"
        } else {
            "Datos Guardados... (OK)"
        }
        db.close()
    }

    fun listarDatosContratos(contratos: Contratos): MutableList<Contratos>{
        var lista: MutableList<Contratos> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM contratos"
        var resultado = db.rawQuery(sql, null)

        if(resultado.moveToFirst()){
            do{
                var contrato = Contratos()

                contratos.idContrato = resultado.getString(0).toInt()
                contratos.idInquilino = resultado.getString(resultado.getColumnIndex("id_inquilino").toInt()).toInt()
                contratos.idInMueble = resultado.getString(resultado.getColumnIndex("id_inmueble").toInt()).toInt()
                contratos.fechaInicial = resultado.getString(resultado.getColumnIndex("fecha_inicial").toInt())
                contratos.fechaFinal = resultado.getString(resultado.getColumnIndex("fecha_final").toInt())
                contratos.observacion = resultado.getString(resultado.getColumnIndex("observaciones").toInt())

                lista.add(contrato)
            } while (resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }

    fun eliminarDatosContratos(id: String): String {
        if (id.length > 0) {
            val db = writableDatabase
            var resultado = db.delete("contratos", "id=?", arrayOf(id))

            if (resultado == 0) {
                return "No se pudo Eliminar Los Datos"
            } else {
                return "Datos Eliminados... (OK)"
            }

            db.close()
        } else {
            return "No se pudo Eliminar Los Datos"
        }
    }

    fun actualizarDatosContrato(id: String, idInquilino: Int, idInmueble: Int, fechaInicial: String, fechaFinal: String, observaciones: String): String {
        val db = this.writableDatabase
        val contenedor = ContentValues()

        contenedor.put("id_inmueble", idInmueble)
        contenedor.put("id_inquilino", idInquilino)
        contenedor.put("fecha_Inicial", fechaInicial)
        contenedor.put("fecha_Final", fechaFinal)
        contenedor.put("observaciones", observaciones)

        var resultado = db.update("contratos", contenedor, "id=?", arrayOf(id))

        if (resultado == 0) {
            return "No se puden Actualizar los Datos"
        } else {
            return "Datos Actualizados (OK)"
        }
    }



}




