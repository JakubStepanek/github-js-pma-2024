package com.example.myapp006toastsnackbar


import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp006toastsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //button actions
        binding.btnShowToast.setOnClickListener {
            showToast()
        }

        binding.btnShowSnackbar.setOnClickListener {
            showSnackbar()
        }
    }

    //
    private fun showSnackbar() {
        Snackbar.make(binding.root, "Když je to pravda, tak je to pravda.", 7000)
            .setBackgroundTint(Color.GREEN)
            .setTextColor(Color.BLACK)
            .setAction("Zavřít") {
                Toast.makeText(this, "Zavírám Snackbar", Toast.LENGTH_SHORT)
                    .show()
            }
            .show()
    }

    private fun showToast() {
        val toast = Toast.makeText(
            this,
            "Směr je tímto směrem, protože jinak by realita neodpovídala skutečnosti.",
            Toast.LENGTH_LONG
        )

        .show()

    }
}