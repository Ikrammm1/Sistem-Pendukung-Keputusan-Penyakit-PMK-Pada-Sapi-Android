package com.myapplication.Login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.myapplication.API.RetrofitClient
import com.myapplication.Beranda.Beranda
import com.myapplication.LupaPass.Cekusername
import com.myapplication.R
import com.myapplication.Register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var BtnLogin : Button
    lateinit var EtUsername : EditText
    lateinit var EtPassword : EditText
    lateinit var txtRegister : TextView
    lateinit var txtLupa : TextView
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //cek session
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        if(profil.getString("id_user",null) !=null) {
            startActivity(Intent(this@LoginActivity, Beranda::class.java))
            finish()
        }

        EtUsername = findViewById(R.id.etUsername)
        EtPassword = findViewById(R.id.etPassword)
        BtnLogin = findViewById(R.id.btnLogin)
        txtRegister = findViewById(R.id.Register)
        txtLupa = findViewById(R.id.txtlupa)
        txtLupa.setOnClickListener {
            startActivity(Intent(this, Cekusername::class.java))
        }
        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity ::class.java))
        }

        BtnLogin.setOnClickListener {

            when{
                EtUsername.text.toString() == "" ->{
                    EtUsername.error = "Username Tidak Boleh Kosong!"
                }
                EtPassword.text.toString() == "" -> {
                    EtPassword.error = "Password Tidak Boleh Kosong!"
                }
                else -> {
                    getUser()
                }
            }

        }
    }
    private fun getUser(){
        RetrofitClient.instance.login(
            EtUsername.text.toString(),
            EtPassword.text.toString()
        ).enqueue(object : Callback<ResponeLogin>{
            override fun onResponse(call: Call<ResponeLogin>, response: Response<ResponeLogin>) {
                if (response.isSuccessful){
                    //fungsi session
                    getSharedPreferences("Login_Session", MODE_PRIVATE)
                        .edit()
                        .putString("id_user", response.body()?.payload?.id_user)
                        .putString("nama_user", response.body()?.payload?.nama_user)
                        .putString("username", response.body()?.payload?.username)
                        .putString("password", response.body()?.payload?.password)
                        .putString("level", response.body()?.payload?.level)
                        .apply()

                    if(response.body()?.response == true) {
                        startActivity(Intent(this@LoginActivity, Beranda::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Username / Password Salah", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Kesalahan", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ResponeLogin>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Maaf Sedang Gangguan, Silahkan Ulangi", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Login : ", t.toString())
            }

        })
    }
}
