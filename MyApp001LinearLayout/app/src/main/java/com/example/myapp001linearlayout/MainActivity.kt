package com.example.myapp001linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.etName)
        val surnameEditText = findViewById<EditText>(R.id.etSurname)
        val cityEditText = findViewById<EditText>(R.id.etCity)
        val ageEditText = findViewById<EditText>(R.id.etAge)
        val sendButton = findViewById<Button>(R.id.btnSend)
        val deleteButton = findViewById<Button>(R.id.btnDelete)
        val resultTextView = findViewById<TextView>(R.id.tvResult)

        // onClick button send
        sendButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val city = cityEditText.text.toString()
            val age = ageEditText.text.toString()

            resultTextView.text = "Jmenuji se $name $surname. Mé bydliště je $city a je mi $age."
        }

        // onClick button delete
        deleteButton.setOnClickListener {
            nameEditText.text.clear()
            surnameEditText.text.clear()
            cityEditText.text.clear()
            ageEditText.text.clear()
            resultTextView.text = ""
        }
    }
}