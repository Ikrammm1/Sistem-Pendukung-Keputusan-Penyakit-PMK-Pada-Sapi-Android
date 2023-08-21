package com.myapplication.LupaPass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myapplication.API.RetrofitClient
import com.myapplication.Login.LoginActivity
import com.myapplication.Model.ModelRespon
import com.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Gantipass : AppCompatActivity() {
    lateinit var EdtPw : EditText
    lateinit var EdtKnfPw : EditText
    lateinit var BtnSimpan : Button
    private val Username by lazy { intent.getStringExtra("Username") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gantipass)

        EdtPw = findViewById(R.id.et_pwbaru)
        EdtKnfPw = findViewById(R.id.et_knfpw)
        BtnSimpan = findViewById(R.id.btn_simpan)

        BtnSimpan.setOnClickListener {

            when {
                EdtPw.text.toString() == "" -> {
                    EdtPw.error = "Password Tidak Boleh Kosong!"
                }
                EdtKnfPw.text.toString() == "" -> {
                    EdtKnfPw.error = "Konfirmasi Password Tidak Boleh Kosong!"
                }
                EdtPw.text.toString() != EdtKnfPw.text.toString() -> {
                    Toast.makeText(this@Gantipass, "Password Tidak Sesuai", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    EditPass()
                }
            }
        }
    }
    private fun EditPass(){
        RetrofitClient.instance.EditPass(Username.toString(), EdtPw.text.toString()).enqueue(object :
            Callback<ModelRespon> {
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                Toast.makeText(this@Gantipass, "Berhasil Ubah Password, Silahkan Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Gantipass, LoginActivity::class.java) )
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@Gantipass, "Mohon Maaf, Sedang Terjadi Kesalhan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
