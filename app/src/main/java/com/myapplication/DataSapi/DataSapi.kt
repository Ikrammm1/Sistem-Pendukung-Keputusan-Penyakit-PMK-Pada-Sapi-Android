package com.myapplication.DataSapi

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.API.RetrofitClient
import com.myapplication.Beranda.Beranda
import com.myapplication.Model.ModelRespon
import com.myapplication.Model.ModelSapi
import com.myapplication.R
import com.myapplication.Ranking.Ranking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSapi : AppCompatActivity() {

    lateinit var Adapter : AdapterSapi
    lateinit var ListItem : RecyclerView
    lateinit var BtnTambah : LinearLayout
    lateinit var BtnHitung : LinearLayout
    lateinit var Ket : TextView
    lateinit var Kriteria : String
    private lateinit var profil : SharedPreferences
    lateinit var  Id : String
    lateinit var JmlData : String
    lateinit var BtnBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_sapi)
        setUpList()
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        Id = profil.getString("id_user", null)!!
        BtnTambah = findViewById(R.id.btn_tambah)
        BtnHitung = findViewById(R.id.btn_Hitung)
        BtnBack = findViewById(R.id.btn_Back)
        Ket = findViewById(R.id.txtKet)
        Kriteria = "Keterangan : \n" +
                "C1 = Jumlah Air Liur\n" +
                "C2 = Kaki Pincang\n" +
                "C3 = Nafsu Makan\n" +
                "C4 = Mulut Dan Lidah Bengkak\n" +
                "C5 =  Postur"
        Ket.text = Kriteria
        BtnTambah.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }
        BtnBack.setOnClickListener {
            startActivity(Intent(this, Beranda::class.java))
        }


        BtnHitung.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Apakah Anda Yakin?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    HitungHasil()

                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                .show()
        }


    }
    override fun onStart(){
        super.onStart()
        getData()

    }

    private fun getData(){
        RetrofitClient.instance.DataSapi(Id).enqueue(object : Callback<ModelSapi>{
            override fun onResponse(call: Call<ModelSapi>, response: Response<ModelSapi>) {
                if (response.isSuccessful){

                    val listData = response.body()!!.Datasapi
                    JmlData = listData.size.toString()
                    if (JmlData != "0"){
                        BtnHitung.visibility = View.VISIBLE
                    }else{
                        BtnHitung.visibility = View.GONE
                    }
                    listData.forEach {
                        Adapter.setData(listData)
                    }
                }
            }

            override fun onFailure(call: Call<ModelSapi>, t: Throwable) {
                Toast.makeText(this@DataSapi, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Datasapi", t.toString())
            }

        })
    }
    private fun setUpList(){
        ListItem = findViewById(R.id.list_sapi)
        Adapter = AdapterSapi(arrayListOf(), object : AdapterSapi.OnAdapterlistener{
            override fun onClick(detail: ModelSapi.dataSapi) {
                startActivity(
                    Intent(this@DataSapi, EditsapiActivity::class.java)
                    .putExtra("Alternatif", detail.alternatif)
                    .putExtra("airliur", detail.airliur)
                    .putExtra("kakipincang", detail.kakipincang)
                    .putExtra("nafsumakan", detail.nafsumakan)
                    .putExtra("lidahbengkak", detail.lidahbengkak)
                    .putExtra("postur", detail.postur)
                    .putExtra("IdSapi", detail.id_datasapi)

                )
            }

            override fun onDelete(detail: ModelSapi.dataSapi) {
                var alertDialog = AlertDialog.Builder(this@DataSapi)
                    .setTitle("Apakah Anda Yakin Ingin Menghapus Data Sapi?")
                    .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                        RetrofitClient.instance.HapusSapi(detail.id_datasapi).enqueue(object :
                            Callback<ModelRespon> {
                            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                                if (response.isSuccessful){
                                    Toast.makeText(this@DataSapi, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                                    getData()
                                }
                            }

                            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                                Toast.makeText(this@DataSapi, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                                Log.e("Kesalahan API Hapus", t.toString())
                            }

                        })

                    })
                    .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                    .show()
            }

        })
        ListItem.adapter = Adapter
    }

    private fun HitungHasil(){
        RetrofitClient.instance.HitungHasil(Id).enqueue(object : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful){
                    startActivity(Intent(this@DataSapi, Ranking::class.java))
                }else{
                    Toast.makeText(this@DataSapi, "Kesalahan", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@DataSapi, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Hitung", t.toString())
            }

        })
    }
}
