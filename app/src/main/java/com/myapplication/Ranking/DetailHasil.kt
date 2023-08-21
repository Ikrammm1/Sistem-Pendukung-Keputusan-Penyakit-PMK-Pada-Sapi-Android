package com.myapplication.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.myapplication.R

class DetailHasil : AppCompatActivity() {
    private val Alternatif by lazy { intent.getStringExtra("Alternatif") }
    private val AirLiur by lazy { intent.getStringExtra("airliur") }
    private val KakiPincang by lazy { intent.getStringExtra("kakipincang") }
    private val NafsuMakan by lazy { intent.getStringExtra("nafsumakan") }
    private val LidahBengkak by lazy { intent.getStringExtra("lidahbengkak") }
    private val Postur by lazy { intent.getStringExtra("postur") }
    private val Nilai by lazy { intent.getStringExtra("Nilai") }
    private val Ranking by lazy { intent.getStringExtra("Ranking") }
    private val Kesimpulan by lazy { intent.getStringExtra("Kesimpulan") }

    lateinit var TxtAlt : TextView
    lateinit var TxtAirliur : TextView
    lateinit var TxtKakiPincang : TextView
    lateinit var TxtNafsuMakan : TextView
    lateinit var TxtLidahBengkak : TextView
    lateinit var TxtPostur : TextView
    lateinit var TxtNilai : TextView
    lateinit var TxtRanking : TextView
    lateinit var TxtKesimpulan : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hasil)

        TxtAlt = findViewById(R.id.txtAlt)
        TxtAirliur = findViewById(R.id.txtAirLiur)
        TxtKakiPincang = findViewById(R.id.txtKakipincang)
        TxtNafsuMakan = findViewById(R.id.txtNafsumakan)
        TxtLidahBengkak = findViewById(R.id.txtLidahbengkak)
        TxtPostur = findViewById(R.id.txtPostur)
        TxtNilai = findViewById(R.id.txtNilai)
        TxtRanking = findViewById(R.id.txtRanking)
        TxtKesimpulan = findViewById(R.id.txtKesimpulan)

        TxtAlt.text = Alternatif.toString()
        TxtAirliur.text = AirLiur.toString()
        TxtKakiPincang.text = KakiPincang.toString()
        TxtNafsuMakan.text = NafsuMakan.toString()
        TxtLidahBengkak.text = LidahBengkak.toString()
        TxtPostur.text = Postur.toString()
        TxtNilai.text = Nilai.toString()
        TxtRanking.text = Ranking.toString()
        TxtKesimpulan.text = Kesimpulan.toString()
    }
}
