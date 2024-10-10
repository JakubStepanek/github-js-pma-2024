package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val btnSecondActivity = findViewById<Button>(R.id.btnSecondSection)
        val etNickname = findViewById<EditText>(R.id.etNickname)

        btnSecondActivity.setOnClickListener {
            val nickname = etNickname.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER_NICKNAME", nickname)
            startActivity(intent)
        }

    }
}