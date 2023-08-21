package com.myapplication.profile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myapplication.API.RetrofitClient
import com.myapplication.Login.LoginActivity
import com.myapplication.Model.ModelRespon
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {
    lateinit var BtnSimpan : Button
    lateinit var EtUsername : EditText
    lateinit var EtNama : EditText
    lateinit var EtPassword : EditText
    private lateinit var profil : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)

        EtNama = findViewById(R.id.etNama)
        EtUsername = findViewById(R.id.etUsername)
        EtPassword = findViewById(R.id.etPassword)
        BtnSimpan = findViewById(R.id.btnSimpan)

        EtNama.setText(profil.getString("nama_user", null).toString())
        EtUsername.setText(profil.getString("username", null).toString())
        EtPassword.setText(profil.getString("password", null).toString())

        BtnSimpan.setOnClickListener {
            when{
                EtNama.text.toString() == ""->{
                    EtNama.error = "Nama Tidak Boleh Kosong!"
                }
                EtUsername.text.toString() == "" ->{
                    EtUsername.error = "Username Tidak Boleh Kosong!"
                }
                EtPassword.text.toString() == "" -> {
                    EtPassword.error = "Password Tidak Boleh Kosong!"
                }
                else->{
                    EditUser()
                }

            }
        }

    }
    private fun EditUser(){
        RetrofitClient.instance.EditUser(
            profil.getString("id_user", null).toString(),
            EtNama.text.toString(),
            EtUsername.text.toString(),
            EtPassword.text.toString()
        ).enqueue(object : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful){
                    if (response.body()!!.status =="Ok"){
                        Toast.makeText(this@Profile, "Ubah Data Berhasil, Silahkan Login Kembali", Toast.LENGTH_SHORT).show()
                        //menghapus session
                        profil.edit().clear().commit()
                        startActivity(Intent(this@Profile, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@Profile, "Maaf Terjadi Kesalahan, Ulangi", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@Profile, "Maaf sedang terjadi gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Edit", t.toString())
            }

        })
    }
}
