package com.myapplication.DataSapi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.myapplication.API.RetrofitClient
import com.myapplication.Model.ModelRespon
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditsapiActivity : AppCompatActivity() {

    private val Alternatif by lazy { intent.getStringExtra("Alternatif") }
    private val AirLiur by lazy { intent.getStringExtra("airliur") }
    private val KakiPincang by lazy { intent.getStringExtra("kakipincang") }
    private val NafsuMakan by lazy { intent.getStringExtra("nafsumakan") }
    private val LidahBengkak by lazy { intent.getStringExtra("lidahbengkak") }
    private val Postur by lazy { intent.getStringExtra("postur") }
    private val IdSapi by lazy { intent.getStringExtra("IdSapi") }

    lateinit var RgAirLiur : RadioGroup
    lateinit var RbNormal : RadioButton
    lateinit var RbBanyak : RadioButton
    lateinit var RgKakiPincang : RadioGroup
    lateinit var RbPincang : RadioButton
    lateinit var RbTdkPincang : RadioButton
    lateinit var RgNafsuMakan : RadioGroup
    lateinit var RbNafsuNormal : RadioButton
    lateinit var RbRendah : RadioButton
    lateinit var RgLidahBengkak : RadioGroup
    lateinit var RbBengkak : RadioButton
    lateinit var RbTdkBengkak : RadioButton
    lateinit var RgPostur : RadioGroup
    lateinit var RbPosturNormal : RadioButton
    lateinit var RbKurus : RadioButton
    lateinit var TxtAlt : EditText
    lateinit var TxtAirLiur : String
    lateinit var TxtKakiPincang : String
    lateinit var TxtNafsuMakan : String
    lateinit var TxtLidahBengkak : String
    lateinit var TxtPostur : String
    lateinit var BtnUbah : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editsapi)

        TxtAlt = findViewById(R.id.txtAlt)
        RgAirLiur = findViewById(R.id.rgAirLiur)
        RbNormal = findViewById(R.id.rbnormal)
        RbBanyak = findViewById(R.id.rbbanyak)
        RgKakiPincang = findViewById(R.id.rgKakiPincang)
        RbPincang = findViewById(R.id.rbpincang)
        RbTdkPincang = findViewById(R.id.rbtdkpincang)
        RgNafsuMakan = findViewById(R.id.rgNafsuMakan)
        RbNafsuNormal = findViewById(R.id.rbnafsunormal)
        RbRendah = findViewById(R.id.rbrendah)
        RgLidahBengkak = findViewById(R.id.rgLidahBengkak)
        RbBengkak = findViewById(R.id.rbbengkak)
        RbTdkBengkak = findViewById(R.id.rbtdkBengkak)
        RgPostur = findViewById(R.id.rgPostur)
        RbPosturNormal = findViewById(R.id.rbposturnormal)
        RbKurus = findViewById(R.id.rbkurus)
        BtnUbah = findViewById(R.id.btnUbah)



       TxtAlt.setText(Alternatif)
       TxtAirLiur = AirLiur.toString()
       TxtKakiPincang = KakiPincang.toString()
       TxtNafsuMakan = NafsuMakan.toString()
       TxtLidahBengkak = LidahBengkak.toString()
       TxtPostur = Postur.toString()

        if (AirLiur.toString() == "Banyak"){
           RbBanyak.isChecked = true

        }else if (AirLiur.toString() == "Normal"){
            RbNormal.isChecked = true

        }

        if(KakiPincang.toString() == "Pincang"){
            RbPincang.isChecked = true
        }else if(KakiPincang.toString() == "Tidak Pincang"){
            RbTdkPincang.isChecked = true
        }

        if(NafsuMakan.toString() == "Normal"){
            RbNafsuNormal.isChecked = true
        }else if(NafsuMakan.toString() == "Rendah"){
            RbRendah.isChecked = true
        }
        if(LidahBengkak.toString() == "Bengkak"){
            RbBengkak.isChecked = true
        }else if(LidahBengkak.toString() == "Tidak Bengkak"){
            RbTdkBengkak.isChecked = true
        }
        if(Postur.toString() == "Normal"){
            RbPosturNormal.isChecked = true
        }else if(Postur.toString() == "Kurus"){
            RbKurus.isChecked = true
        }

        RgAirLiur.setOnCheckedChangeListener { group, checkedId ->
            TxtAirLiur = when(checkedId){
                R.id.rbbanyak -> "Banyak"
                R.id.rbnormal -> "Normal"
                else -> ""
            }
        }
        RgKakiPincang.setOnCheckedChangeListener { group, checkedId ->
            TxtKakiPincang = when(checkedId){
                R.id.rbpincang -> "Pincang"
                R.id.rbtdkpincang -> "Tidak Pincang"
                else -> ""
            }
        }
        RgNafsuMakan.setOnCheckedChangeListener { group, checkedId ->
            TxtNafsuMakan = when(checkedId){
                R.id.rbnafsunormal -> "Normal"
                R.id.rbrendah -> "Rendah"
                else -> ""
            }
        }
        RgLidahBengkak.setOnCheckedChangeListener { group, checkedId ->
            TxtLidahBengkak = when(checkedId){
                R.id.rbbengkak -> "Bengkak"
                R.id.rbtdkBengkak -> "Tidak Bengkak"
                else -> ""
            }
        }
        RgPostur.setOnCheckedChangeListener { group, checkedId ->
            TxtPostur = when(checkedId){
                R.id.rbposturnormal -> "Normal"
                R.id.rbkurus -> "Kurus"
                else -> ""
            }
        }

        BtnUbah.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Apakah Anda ingin Mengubah Data Sapi?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    EditSapi()

                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                .show()
        }

    }
    private fun EditSapi(){
        RetrofitClient.instance.UpdateSapi(
            IdSapi.toString(),
            TxtAlt.text.toString(),
            TxtAirLiur,
            TxtKakiPincang,
            TxtNafsuMakan,
            TxtLidahBengkak,
            TxtPostur
        ).enqueue(object :Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful){
                    Toast.makeText(this@EditsapiActivity, "Ubah Data Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@EditsapiActivity, DataSapi::class.java))
                }else{
                    Toast.makeText(this@EditsapiActivity, "Terjadi Kesalahan. Ulangi!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@EditsapiActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API EditData", t.toString())
            }

        })
    }
}
