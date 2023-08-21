package com.myapplication.Kriteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.API.RetrofitClient
import com.myapplication.Model.ModelKriteria
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Kriteria : AppCompatActivity() {
    lateinit var Adapter : AdapterKriteria
    lateinit var listItem : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kriteria)
        setUpList()
    }
    override fun onStart(){
        super.onStart()
        getData()
    }
    private fun getData(){
        RetrofitClient.instance.Kriteria().enqueue(object : Callback<ModelKriteria> {
            override fun onResponse(
                call: Call<ModelKriteria>,
                response: Response<ModelKriteria>
            ) {
                if (response.isSuccessful){
                    val ListData = response.body()!!.Kriteria
                    ListData.forEach {
                        Adapter.setData(ListData)
                    }
                } else{
                    Toast.makeText(this@Kriteria, "Maaf Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelKriteria>, t: Throwable) {
                Toast.makeText(this@Kriteria, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Kriteria : ", t.toString())
            }

        })
    }
    private fun setUpList(){
        listItem = findViewById(R.id.listKriteria)
        Adapter = AdapterKriteria(arrayListOf())
        listItem.adapter = Adapter
    }
}
