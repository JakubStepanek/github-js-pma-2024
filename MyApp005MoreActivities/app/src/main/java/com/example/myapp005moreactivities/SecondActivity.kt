package com.example.myapp005moreactivities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContentView(R.layout.activity_second)

        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        // get data from Intent
        val nickname = intent.getStringExtra("USER_NICKNAME")
        tvInfo.text = "Data z první aktivity. Přezdívka: $nickname"

        val btnClose = findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish()
        }

        val btnThirdActivity = findViewById<Button>(R.id.btnThirdSection)
    }
}