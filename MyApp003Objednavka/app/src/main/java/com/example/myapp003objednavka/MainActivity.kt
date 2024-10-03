package com.example.myapp003objednavka

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp003objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()


        // binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Objednávka motocyklu"

        binding.btnSubmit.setOnClickListener {
            // get id of checked radiobutton
            val idRBMotorcycle = binding.rgMotorcycles.checkedRadioButtonId

            val motorcycle = findViewById<RadioButton>(idRBMotorcycle)

            val electricSuspension = binding.cbElectricSuspension.isChecked
            val seat = binding.cbSeat.isChecked
            val racingPacket = binding.cbRacingPacket.isChecked

            val orderText = "Souhrn objednávky: " + "${motorcycle.text}" +
                    (if (electricSuspension) "; elektrický podvozek" else "") +
                    (if (seat) "; lepší sedlo" else "") +
                    (if (racingPacket) "; krutopřísný paket" else "")
            binding.tvOrder.text = orderText
        }

        // Changing the image based on the selected radio button

        binding.rbMotorcycle1.setOnClickListener {
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v2)
        }

        binding.rbMotorcycle2.setOnClickListener {
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v4)
        }

        binding.rbMotorcycle3.setOnClickListener {
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v4_r)
        }
    }
}