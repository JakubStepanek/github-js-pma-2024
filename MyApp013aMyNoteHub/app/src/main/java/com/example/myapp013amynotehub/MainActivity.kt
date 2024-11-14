package com.example.myapp013amynotehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp013amynotehub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)
    }
}