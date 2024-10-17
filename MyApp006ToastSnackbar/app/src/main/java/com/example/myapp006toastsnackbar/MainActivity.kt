package com.example.myapp006toastsnackbar

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp006toastsnackbar.databinding.ActivityMainBinding
import com.example.myapp006toastsnackbar.databinding.CustomToastBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding for main layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button actions
        binding.btnShowToast.setOnClickListener {
            showToast()
        }

        binding.btnShowSnackbar.setOnClickListener {
            showSnackbar()
        }
    }

    // Show Snackbar
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

    // Show custom Toast with image and text using ViewBinding
    private fun showToast() {
        // Initialize ViewBinding for the custom toast layout
        val toastBinding = CustomToastBinding.inflate(layoutInflater)

        // Set image and text for the Toast using binding
        toastBinding.toastImage.setImageResource(R.drawable.ic_toast_icon) // Replace with your own image
        toastBinding.toastText.text = "Směr je tímto směrem, protože jinak by realita neodpovídala skutečnosti."

        // Create the custom Toast
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG

        // Set the custom view for the Toast
        toast.view = toastBinding.root // Deprecated, but still functional below API 30

        // Set the position of the Toast if needed
        toast.setGravity(Gravity.BOTTOM, 0, 100)

        toast.show()
    }
}
