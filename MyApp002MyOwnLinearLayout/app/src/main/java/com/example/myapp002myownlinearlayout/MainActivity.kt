package com.example.myapp002myownlinearlayout

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets

        // inicializace komponent
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnCommend = findViewById<Button>(R.id.btnCommend)
        val tvBMIResult = findViewById<TextView>(R.id.tvBMIResult)
        val tvBMIMessage = findViewById<TextView>(R.id.tvBMIMessage)

        // calculate on click
        btnCalculate.setOnClickListener {
            // načtení hodnoty ze vstupních polí
            var height = etHeight.text.toString().toFloat()
            var weight = etWeight.text.toString().toFloat()
            // výpočet BMI a zobrazení výsledku
            var bmi = calculateBMI(height, weight)
            tvBMIResult.text = bmi.toString()
            tvBMIMessage.text = ""
        }
        // výpis pochvaly
        btnCommend.setOnClickListener {
            val bmi = tvBMIResult.text.toString().toFloat()

            // Získání zprávy a ID barvy
            val (message, colorRes) = getResultMessageWithColor(bmi)

            // Načtení barvy pomocí ContextCompat
            val color = ContextCompat.getColor(this, colorRes)

            // Nastavení barvy a zprávy
            tvBMIMessage.setTextColor(color)
            tvBMIMessage.text = message
        }
    }


    fun getResultMessageWithColor(bmiValue: Float): Pair<String, Int> {
        val (message, color) = when {
            bmiValue < 18.5 -> "Jsi lehčí než většina lidí, možná bys měl zvážit, jak přidat pár kilogramů!" to R.color.underweight
            bmiValue in 18.5..24.9 -> "Skvělá práce! Udržuješ si zdravou váhu, jen tak dál!" to R.color.normal_weight
            bmiValue in 25.0..29.9 -> "Jsi blízko zdravé váhy! Možná je čas se více zaměřit na pohyb a jídelníček." to R.color.overweight
            else -> "Tvoje BMI naznačuje obezitu. Dbej na své zdraví, zaměř se na zdravější návyky." to R.color.obese
        }
        return message to color
    }

    fun calculateBMI(height: Float, weight: Float): String {
        var bmi = weight / (height / 100 * height / 100)
        return String.format("%.2f", bmi)
    }
}
