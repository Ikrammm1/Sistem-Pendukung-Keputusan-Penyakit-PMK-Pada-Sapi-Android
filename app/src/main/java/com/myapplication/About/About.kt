package com.myapplication.About

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.myapplication.API.RetrofitClient
import com.myapplication.Model.ModelAbout
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class About : AppCompatActivity() {
    lateinit var txtTentang : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        txtTentang = findViewById(R.id.txtTentang)

        RetrofitClient.instance.About().enqueue(object : Callback<ModelAbout> {
            override fun onResponse(call: Call<ModelAbout>, response: Response<ModelAbout>) {
                txtTentang.text = response.body()!!.about.toString()
            }

            override fun onFailure(call: Call<ModelAbout>, t: Throwable) {
                Toast.makeText(this@About, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API About", t.toString())
            }

        })
    }
}
