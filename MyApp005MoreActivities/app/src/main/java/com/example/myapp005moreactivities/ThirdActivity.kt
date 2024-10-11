package com.example.myapp005moreactivities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContentView(R.layout.activity_third)

        title = "Třetí aktivita"

        //binding settings
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nickname = intent.getStringExtra("USER_NICKNAME")
        val favouriteWord = intent.getStringExtra("USER_FAVOURITE_WORD")
        binding.tvInfoFromSecondActivity.text =
            "Data z první aktivity: Přezdívka: $nickname \nData ze druhé: Oblíbené slovo: $favouriteWord"

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}