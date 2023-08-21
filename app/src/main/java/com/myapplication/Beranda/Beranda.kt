package com.myapplication.Beranda

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.myapplication.About.About
import com.myapplication.DataSapi.DataSapi
import com.myapplication.Kriteria.Kriteria
import com.myapplication.Login.LoginActivity
import com.myapplication.R
import com.myapplication.Ranking.Ranking
import com.myapplication.profile.Profile

class Beranda : AppCompatActivity() {

    lateinit var Prof : ImageView
    lateinit var txtNama : TextView
    lateinit var BtnLogout : Button
    lateinit var BtnInput : CardView
    lateinit var BtnKriteria : CardView
    lateinit var BtnHasil : CardView
    lateinit var BtnAbout : CardView
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        val NamaUser = profil.getString("nama_user", null).toString()


        txtNama = findViewById(R.id.title)
        BtnLogout = findViewById(R.id.btnLogout)
        BtnInput = findViewById(R.id.diagnosa)
        BtnKriteria = findViewById(R.id.tentang)
        BtnHasil = findViewById(R.id.ranking)
        BtnAbout = findViewById(R.id.about)
        Prof = findViewById(R.id.prof)

        txtNama.text = "Selamat Datang ${NamaUser} \n di Sistem Diagnosa Penyakit PMK Sapi "

        Prof.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
        BtnInput.setOnClickListener {
            startActivity(Intent(this, DataSapi::class.java))
        }
        BtnHasil.setOnClickListener {
            startActivity(Intent(this, Ranking::class.java))
        }
        BtnKriteria.setOnClickListener {
            startActivity(Intent(this, Kriteria::class.java))
        }
        BtnAbout.setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }

        BtnLogout.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Apakah Anda Yakin Ingin Keluar?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    //menghapus session
                    profil.edit().clear().commit()

                    val kelogin = Intent (this@Beranda, LoginActivity::class.java)
                    startActivity(kelogin)

                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                .show()

        }
    }
}
