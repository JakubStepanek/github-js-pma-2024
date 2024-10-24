package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class DetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewTeam: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        textViewName = view.findViewById(R.id.textViewName)
        textViewTeam = view.findViewById(R.id.textViewTeam)
        textViewDescription = view.findViewById(R.id.textViewDescription)
        imageView = view.findViewById(R.id.imageView)

        // Načtení argumentů a aktualizace textových polí
        arguments?.let {
            val name = it.getString("name")
            val team = it.getString("team")
            val description = it.getString("description")
            val imageURL = it.getInt("imageUrl")
            updateDetails(
                name ?: "Unknown",
                team ?: "Unknown",
                description ?: "No description available",
                imageURL ?: 0
            )
        }

        return view
    }

    // Metoda pro aktualizaci zobrazení detailů
    fun updateDetails(name: String, team: String, description: String, imageURL: Int) {
        textViewName.text = name
        textViewTeam.text = team
        textViewDescription.text = description
        imageView.setImageResource(imageURL)
    }
}