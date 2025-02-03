package com.example.myapp015avanocniappka
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp015avanocniappka.CalendarFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Načtení CalendarFragmentu do kontejneru
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CalendarFragment())
                .commit()
        }
    }
}
