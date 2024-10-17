package com.example.myapp005moreactivities

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp005moreactivities.databinding.ActivityThirdBinding
import com.google.android.material.snackbar.Snackbar

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContentView(R.layout.activity_third)


        //binding settings
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //title setup
        title = "Třetí aktivita"
        binding.tvTitle.text = title


        val nickname = intent.getStringExtra("USER_NICKNAME")
        val favouriteWord = intent.getStringExtra("USER_FAVOURITE_WORD")
        binding.tvInfoFromSecondActivity.text =
            "Data z první aktivity: Přezdívka: $nickname \nData ze druhé: Oblíbené slovo: $favouriteWord"

        binding.btnClose.setOnClickListener {
            showAlertDialog()
            showDataBySnackbar()
        }
    }

    private fun showDataBySnackbar() {
        val nickname = intent.getStringExtra("USER_NICKNAME")

        Snackbar.make(
            binding.root,
            "Uživatel $nickname bude vymazán.",
            Snackbar.LENGTH_INDEFINITE
        )
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.RED)
            .setAction("OK") {
                // finish() kdyby náhodou nebylo využito AlertDialogu
            }
            .show()
    }


    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val nickname = intent.getStringExtra("USER_NICKNAME")

        builder.setTitle("SMAZAT UŽIVATELE")
        builder.setMessage("Opravdu chcete vymazat data uživatele \n$nickname?")

        // Tlačítko "ANO"
        builder.setPositiveButton("ANO") { dialog, _ ->
            dialog.dismiss()
            finish() // Zavření aktivity
        }

        // Tlačítko "NE"
        builder.setNegativeButton("NE") { dialog, _ ->
            dialog.dismiss() // Zavření dialogu, bez akce
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }


}