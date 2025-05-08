package io.issc.android_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.fragment.app.Fragment
import io.issc.android_tutorial_kt.databinding.FragmentP2Binding

class FragmentP2 : Fragment() {
    lateinit var binding: FragmentP2Binding
    lateinit var calendar: CalendarView;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentP2Binding.inflate(layoutInflater)

        calendar = binding.calendar;

        calendar.setOnDateChangeListener(object: OnDateChangeListener{
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {
                binding.txt.text = "$year-$month-$dayOfMonth";
            }
        })

        return binding.root
    }
}