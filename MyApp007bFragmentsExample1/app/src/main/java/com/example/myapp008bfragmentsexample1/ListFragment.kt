package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private val riders = listOf(
        Rider(
            "Valentino Rossi",
            "Legendární italský závodník",
            "Yamaha",
            R.drawable.valentino_rossi_helmet
        ),
        Rider(
            "Francesco Bagnaia",
            "Italský jezdec Ducati",
            "Ducati",
            R.drawable.francesco_bagnaia_suit
        ),
        Rider("Franco Morbidelli", "Italský jezdec Yamahy", "Yamaha", R.drawable.franco_morbidelli)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listViewRiders)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            riders.map { it.name }
        )
        listView.adapter = adapter

        // Při kliknutí na položku zavoláme metodu aktivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRider = riders[position]
            (activity as? MainActivity)?.onRiderSelected(
                selectedRider.name,
                selectedRider.team,
                selectedRider.description,
                selectedRider.profilePictureUrl
            )
        }
        return view
    }
}