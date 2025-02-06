package com.example.myapp015avanocniappka

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment() {

    private lateinit var gridCalendar: GridView
    private lateinit var resetButton: Button
    private lateinit var adapter: CalendarAdapter
    private val unlockedDays: MutableSet<Int> = mutableSetOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridCalendar = view.findViewById(R.id.gridCalendar)
        resetButton = view.findViewById(R.id.btnReset)

        // Načtení odemčených dnů ze SharedPreferences
        val prefs =
            requireActivity().getSharedPreferences("AdventCalendarPrefs", Context.MODE_PRIVATE)
        unlockedDays.addAll(prefs.getStringSet("unlockedDays", emptySet())?.map { it.toInt() }
            ?: emptyList())

        adapter = CalendarAdapter(requireContext(), unlockedDays)
        gridCalendar.adapter = adapter

        gridCalendar.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val day = position + 1
            if (isDayAvailable(day)) {
                // Přejdeme do detailu dne
                val detailFragment = DayDetailFragment.newInstance(day)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .addToBackStack(null)
                    .commit()

                // Uložíme informaci, že den byl odemčen
                if (!unlockedDays.contains(day)) {
                    unlockedDays.add(day)
                    prefs.edit()
                        .putStringSet("unlockedDays", unlockedDays.map { it.toString() }.toSet())
                        .apply()
                    adapter.notifyDataSetChanged()
                }
            } else {
                // Den ještě není dostupný – případně zobrazit Toast nebo Snackbar
            }
        }

        // Nastavení reset tlačítka
        resetButton.setOnClickListener {
            // Vymazání uložených odemčených dnů
            unlockedDays.clear()
            prefs.edit().remove("unlockedDays").apply()
            adapter.notifyDataSetChanged()
        }
    }

    /**
     * Funkce kontroluje, zda je den dostupný k odemknutí.
     * Pro jednoduchost vždy vrací true.
     * V reálné aplikaci by se porovnávalo aktuální datum s dnem.
     */
    private fun isDayAvailable(day: Int): Boolean {
        // test only (opens all)
        return true
        //val currentDay = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH)
        //return currentDay >= day

    }
}
