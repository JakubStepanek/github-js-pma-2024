package com.example.myapp004objednavka

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp004objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Objednávka Motocyklu"

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
        // image change on selected radio button
        binding.rbMotorcycle1.setOnClickListener{
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v2.png)
        }
        binding.rbMotorcycle2.setOnClickListener{
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v4.png)
        }
        binding.rbMotorcycle3.setOnClickListener{
            binding.ivMotorcycle.setImageResource(R.drawable.panigale_v4_r.png)
        }
    }
}