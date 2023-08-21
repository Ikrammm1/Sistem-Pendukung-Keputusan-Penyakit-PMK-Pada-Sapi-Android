package com.myapplication.LupaPass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myapplication.API.RetrofitClient
import com.myapplication.Model.ModelRespon
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cekusername : AppCompatActivity() {
    lateinit var EdtUsername : EditText
    lateinit var BtnGanti : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cekusername)

        EdtUsername = findViewById(R.id.et_username)
        BtnGanti = findViewById(R.id.btn_ganti)

        BtnGanti.setOnClickListener {
            CekUser()
        }
    }
    private fun CekUser(){
        RetrofitClient.instance.CekUser(EdtUsername.text.toString()).enqueue(object :
            Callback<ModelRespon> {
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful && response.body()!!.status == "ok"){
                    Toast.makeText(this@Cekusername, "Silahkan Masukkan Password Baru", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(this@Cekusername, Gantipass::class.java)
                        .putExtra("Username", EdtUsername.text.toString()))
                }else if(response.body()!!.status == "nodata"){
                    Toast.makeText(this@Cekusername, "Username Tidak Terdaftar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@Cekusername, "Mohon Maaf sedang terjadi kesalahan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api Cek : ", t.toString())
            }

        })
    }
}
