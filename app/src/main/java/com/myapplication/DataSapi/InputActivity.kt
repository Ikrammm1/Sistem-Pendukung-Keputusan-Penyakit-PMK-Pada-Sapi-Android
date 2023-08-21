package com.myapplication.DataSapi

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.myapplication.API.RetrofitClient
import com.myapplication.Model.ModelRespon
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    lateinit var Alt : EditText
    lateinit var txtAirLiur : String
    lateinit var txtKakiPincang : String
    lateinit var txtNafsuMakan : String
    lateinit var txtLidahBengkak : String
    lateinit var txtPostur : String
    lateinit var BtnTambah : Button
    private lateinit var profil : SharedPreferences
    lateinit var  Id : String
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        Id = profil.getString("id_user", null)!!

        Alt = findViewById(R.id.et_alt)
        BtnTambah = findViewById(R.id.btn_tambah)
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

        if(RbNormal.isChecked == true){
            txtAirLiur= "Normal"
        }
        if(RbPincang.isChecked == true){
            txtKakiPincang = "Pincang"
        }
        if(RbNafsuNormal.isChecked == true){
            txtNafsuMakan = "Normal"
        }
        if(RbBengkak.isChecked == true){
            txtLidahBengkak = "Bengkak"
        }
        if(RbPosturNormal.isChecked == true){
            txtPostur = "Normal"
        }

        RgAirLiur.setOnCheckedChangeListener { group, checkedId ->
            txtAirLiur = when(checkedId){
                R.id.rbbanyak -> "Banyak"
                R.id.rbnormal -> "Normal"
                else -> ""
            }
        }
        RgKakiPincang.setOnCheckedChangeListener { group, checkedId ->
            txtKakiPincang = when(checkedId){
                R.id.rbpincang -> "Pincang"
                R.id.rbtdkpincang -> "Tidak Pincang"
                else -> ""
            }
        }
        RgNafsuMakan.setOnCheckedChangeListener { group, checkedId ->
            txtNafsuMakan = when(checkedId){
                R.id.rbnafsunormal -> "Normal"
                R.id.rbrendah -> "Rendah"
                else -> ""
            }
        }
        RgLidahBengkak.setOnCheckedChangeListener { group, checkedId ->
            txtLidahBengkak = when(checkedId){
                R.id.rbbengkak -> "Bengkak"
                R.id.rbtdkBengkak -> "Tidak Bengkak"
                else -> ""
            }
        }
        RgPostur.setOnCheckedChangeListener { group, checkedId ->
            txtPostur = when(checkedId){
                R.id.rbposturnormal -> "Normal"
                R.id.rbkurus -> "Kurus"
                else -> ""
            }
        }


        BtnTambah.setOnClickListener {
            when {
                Alt.text.toString() == "" -> {
                    Alt.error = "Nama / Lebel Tidak Boleh Kosong!"
                }
                else ->{
                    var alertDialog = AlertDialog.Builder(this)
                        .setTitle("Apakah Anda ingin Menambahkan Data Sapi?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                            Input()

                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                        .show()

                }

            }
        }



    }
    private fun Input(){
        RetrofitClient.instance.InputSapi(
            Id,
            Alt.text.toString(),
            txtAirLiur,
            txtKakiPincang,
            txtNafsuMakan,
            txtLidahBengkak,
            txtPostur
        ).enqueue(object : Callback<ModelRespon> {
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful){
                    Toast.makeText(this@InputActivity, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@InputActivity, DataSapi::class.java))
                }else{
                    Toast.makeText(this@InputActivity, "Terjadi Kesalahan. Ulangi!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@InputActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Input", t.toString())
            }

        })
    }
}
