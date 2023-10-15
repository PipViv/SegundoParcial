package com.example.segundoparcial;

class InMuebles {

    var idInMueble: Int = 0;
    var nombreInMueble: String = "";
    var tipoInMueble: String = "";
    var direccionInMueble: String = "";
    var foto: ByteArray? = null;

    constructor(nombreInMueble: String, tipoInMueble: String, direccionInMueble: String, foto: ByteArray) {
        this.nombreInMueble = nombreInMueble;
        this.tipoInMueble = tipoInMueble;
        this.direccionInMueble = direccionInMueble;
        this.foto = foto;
    }
    constructor(){

    }



}
