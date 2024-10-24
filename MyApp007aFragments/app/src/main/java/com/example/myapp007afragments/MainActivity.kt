package com.example.myapp007afragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapp007afragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.btnFragment2.setOnClickListener {
            replaceFragment(Fragment2())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        // gets instance of fragment manager
        val fragmentManager = supportFragmentManager
        // create a new transaction with fragments
        val fragmentTransaction = fragmentManager.beginTransaction()
        // replace the fragment in Container with new one
        fragmentTransaction.replace(binding.fragmentContainer.id, fragment)
        // commit the transaction
        fragmentTransaction.commit()
    }
}