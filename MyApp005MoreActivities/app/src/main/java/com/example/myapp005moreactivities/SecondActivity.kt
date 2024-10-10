package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivityMainBinding
import com.example.myapp005moreactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        title = "Sekundární aktivita"

        // binding settings
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nickname = intent.getStringExtra("USER_NICKNAME")
        binding.tvInfo.text = "Data z první aktivity. Přezdívka: $nickname"

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}


//        val tvInfo = findViewById<TextView>(R.id.tvInfo)
//        // get data from Intent
//        val nickname = intent.getStringExtra("USER_NICKNAME")
//        tvInfo.text = "Data z první aktivity. Přezdívka: $nickname"
//
//        val btnClose = findViewById<Button>(R.id.btnClose)
//        btnClose.setOnClickListener {
//            finish()
//        }
//
//        val btnThirdActivity = findViewById<Button>(R.id.btnThirdSection)
//    }
