package com.example.myapp011ajednoduchamatematika

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp011ajednoduchamatematika.databinding.ActivityPlayBinding


class PlayActivity : AppCompatActivity() {
    private lateinit var bindingPlay: ActivityPlayBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingPlay = ActivityPlayBinding.inflate(layoutInflater)

        setContentView(bindingPlay.root)

        // Instanční inicializace
        val addition = bindingPlay.addition
        val sub = bindingPlay.sub
        val multi = bindingPlay.multi
        val division = bindingPlay.division

        addition.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "+")
            startActivity(calInt)
        }

        sub.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "-")
            startActivity(calInt)
        }

        multi.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "*")
            startActivity(calInt)
        }

        division.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "/")
            startActivity(calInt)
        }


    }
}