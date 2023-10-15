package com.example.segundoparcial

class Inquilinos {
    var idInquilino:Int = 0
    var codigoInquilino:Int=0
    var nombreInquilino:String=""
    var apellidoInquilino:String=""
    var telefonoInquilino:Int=0
    var emailInquilino:String=""

    constructor(codigo:Int, nombre:String, apellido:String, telefono:Int, email:String){
        this.codigoInquilino = codigo
        this.nombreInquilino = nombre
        this.apellidoInquilino = apellido
        this.telefonoInquilino = telefono
        this.emailInquilino = email
    }

    constructor(){

    }

}