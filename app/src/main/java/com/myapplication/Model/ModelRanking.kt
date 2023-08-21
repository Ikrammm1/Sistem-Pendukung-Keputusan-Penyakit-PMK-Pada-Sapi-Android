package com.myapplication.Model

class ModelRanking (
    val Ranking : List<dataRanking>
){
    data class dataRanking(
        val  id_datasapi : String,
        val  alternatif : String,
        val  airliur : String,
        val  kakipincang : String,
        val  nafsumakan : String,
        val  lidahbengkak : String,
        val  postur : String,
        val  nilai : String,
        val  ranking : String,
        val  kesimpulan : String
    )
}