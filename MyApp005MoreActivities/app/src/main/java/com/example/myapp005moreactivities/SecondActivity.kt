package com.example.myapp005moreactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp005moreactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        // binding settings
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // title setup
        title = "Sekundární aktivita"
        binding.tvTitle.text = title

        val nickname = intent.getStringExtra("USER_NICKNAME")
        binding.tvInfo.text = "Data z první aktivity. Přezdívka: $nickname"

        binding.btnThirdSection.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("USER_NICKNAME", nickname)
            intent.putExtra("USER_FAVOURITE_WORD", binding.etFavouriteWord.text.toString())
            startActivity(intent)
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnClearData.setOnClickListener {
            binding.etFavouriteWord.text.clear()
        }
    }
}
