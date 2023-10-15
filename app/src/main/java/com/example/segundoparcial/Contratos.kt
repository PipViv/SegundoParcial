package com.example.segundoparcial

import java.util.Date

class Contratos {

    var idContrato: Int = 0
    var idInquilino: Int = 0;
    var idInMueble: Int = 0;
    var fechaInicial: String = "";
    var fechaFinal: String = "";
    var observacion: String = "";


    constructor() {

    }

    constructor(
        idContrato: Int,
        idInquilino: Int,
        idInMueble: Int,
        fechaInicial: String,
        fechaFinal: String,
        observacion: String
    ) {
        this.idContrato = idContrato
        this.idInquilino = idInquilino
        this.idInMueble = idInMueble
        this.fechaInicial = fechaInicial
        this.fechaFinal = fechaFinal
        this.observacion = observacion
    }
}