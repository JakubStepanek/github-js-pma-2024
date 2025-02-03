package com.example.myapp015avanocniappka

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CalendarAdapter(private val context: Context, private val unlockedDays: Set<Int>) : BaseAdapter() {
    override fun getCount(): Int = 24  // Dny 1-24

    override fun getItem(position: Int): Any = position + 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val day = position + 1
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)
        val tvDay = view.findViewById<TextView>(R.id.tvDay)
        tvDay.text = day.toString()
        // Nastavení pozadí dle toho, zda byl den odemčen
        if (unlockedDays.contains(day)) {
            tvDay.setBackgroundResource(R.drawable.bg_day_unlocked)
        } else {
            tvDay.setBackgroundResource(R.drawable.bg_day_locked)
        }
        return view
    }
}
