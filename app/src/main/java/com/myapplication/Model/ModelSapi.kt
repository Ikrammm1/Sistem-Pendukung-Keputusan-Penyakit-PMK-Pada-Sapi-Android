package com.myapplication.Model

class ModelSapi (
    val Datasapi : List<dataSapi>
){
    data class dataSapi(
        val  id_datasapi : String,
        val  alternatif : String,
        val  airliur : String,
        val  kakipincang : String,
        val  nafsumakan : String,
        val  lidahbengkak : String,
        val  postur : String,
        val  C1 : String,
        val  C2 : String,
        val  C3 : String,
        val  C4 : String,
        val  C5 : String
    )
}