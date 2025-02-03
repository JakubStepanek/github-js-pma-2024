package com.example.myapp015avanocniappka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DayDetailFragment : Fragment() {

    private var day: Int = 1

    companion object {
        private const val ARG_DAY = "arg_day"

        fun newInstance(day: Int): DayDetailFragment {
            val fragment = DayDetailFragment()
            val args = Bundle()
            args.putInt(ARG_DAY, day)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        day = arguments?.getInt(ARG_DAY) ?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val ivContent = view.findViewById<ImageView>(R.id.ivContent)

        // Vybereme obsah na základě dne
        val contentItem: AdventItem = if (day == 24) {
            AdventItem(
                title = "VELESELÉ VÁNOVE",
                text = "Dnes máte speciální dárek! Užijte si svátky s radostí a štěstím.",
                imageName = "present.jpg"
            )
        } else {
            sampleItems.random()  // Náhodně vybraný obsah z pole sampleItems
        }

        // Nastavíme text a obrázek
        tvContent.text = "${contentItem.title}\n\n${contentItem.text}"
        // Pro zobrazení obrázku předpokládáme, že obrázky máte v adresáři res/drawable.
        // Název obrázku bez přípony použijeme k získání resource ID.
        val resourceId = resources.getIdentifier(
            contentItem.imageName.substringBeforeLast('.'),
            "drawable",
            requireActivity().packageName
        )
        if (resourceId != 0) {
            ivContent.setImageResource(resourceId)
        } else {
            // Pokud obrázek není nalezen, lze nastavit výchozí obrázek
            ivContent.setImageResource(R.drawable.default_picture)
        }
    }
}
