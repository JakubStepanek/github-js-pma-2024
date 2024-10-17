package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        // binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //title setup
        title = "Hlavní aktivita"
        binding.tvTitle.text = title

        binding.btnSecondSection.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER_NICKNAME", nickname)
            startActivity(intent)
            showNicknameToast()
        }

        binding.btnClearData.setOnClickListener {
            binding.etNickname.text.clear()
        }
    }

    private fun showNicknameToast() {
        val nickname = binding.etNickname.text.toString()
        val toast = Toast.makeText(this, "Zadaná přezdívka je " + nickname, Toast.LENGTH_LONG)
            .show()
    }
}