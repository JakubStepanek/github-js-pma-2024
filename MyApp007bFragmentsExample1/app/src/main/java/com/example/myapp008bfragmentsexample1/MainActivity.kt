package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Přidáme ListFragment, pokud ještě neexistuje
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerList, listFragment)
                .commit()
        }
    }

    // Voláno při výběru knihy
    fun onRiderSelected(name: String, team: String, description: String, imageUrl: Int) {
        // Vytvoření nového DetailFragment a nastavení argumentů
        val detailFragment = DetailFragment()

        val bundle = Bundle().apply {
            putString("name", name)
            putString("team", team)
            putString("description", description)
            putInt("imageUrl", imageUrl)
        }
        detailFragment.arguments = bundle

        // Nahradíme starý fragment novým a commitneme transakci
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerDetail, detailFragment)
            .commit()
    }
}
