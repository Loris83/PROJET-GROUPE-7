package com.example.projet.reserver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.example.projet.databinding.FragmentBookingBinding
import com.example.projet.reservation.ReservationDataModel
import com.example.projet.reservation.ReservationModel
import com.example.projet.user.UserSession
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


class BookingFragment : Fragment() {
    private lateinit var binding: FragmentBookingBinding
    private var currentDate : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBookingBinding.inflate(layoutInflater)
        val buttons =
        listOf<Pair<Button, String>>(
            Pair(binding.button7h00,"07:00"),
            Pair(binding.button8h00, "08:00"),
            Pair(binding.button9h00, "09:00"),
            Pair(binding.button10h00,"10:00"),
            Pair(binding.button11h00,"11:00"),
            Pair(binding.button12h00,"12:00"),
            Pair(binding.button13h00,"13:00"),
            Pair(binding.button14h00,"14:00"),
            Pair(binding.button15h00,"15:00"),
            Pair(binding.button16h00,"16:00"),
            Pair(binding.button17h00,"17:00"),
            Pair(binding.button18h00,"18:00"),
            Pair(binding.button19h00,"19:00"),
            Pair(binding.button20h00,"20:00"),
            Pair(binding.button21h00,"21:00")
        )

        buttons.forEach { horaire ->
            horaire.first.setOnClickListener {
                currentDate?.let {
                    ReservationModel.addReservation(
                        ReservationDataModel(
                            UUID.randomUUID().toString(),
                            horaire.second,
                            "1h",
                            it,
                            "",
                            UserSession.user.id
                        )

                    )
                    horaire.first.isEnabled = false
                }
            }
        }

        binding.spinnerCourt.apply {
            adapter = SpinnerCourtAdapter(binding.root.context, listOf(
                "terrain 1", "terrain 2"
            ))
        }

        ReservationModel.findReservation {
            buttons.forEach {
                it.first.isEnabled = true
            }
            it.filter { it.date.equals(currentDate) }.forEach{
                    res->
                buttons.first { it.second.equals(res.opening) }.first.isEnabled = false
            }

        }

        binding.calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{

            override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, day: Int) {
                currentDate = getFormatedDate(day,month,year)
                ReservationModel.findReservation {
                    var cnt = 0
                    buttons.forEach {
                        it.first.isEnabled = true
                        ++cnt
                    }
                    if(cnt >= 2){
                        buttons.forEach {
                            it.first.isEnabled = false
                        }
                    }
                    it.filter { it.date.equals(currentDate) }.forEach{
                        res->
                        buttons.first { it.second.equals(res.opening) }.first.isEnabled = false


                    }

                }
            }

        }

        )


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun getFormatedDate(date : Date) : String{
        var day = (date.day+1).toString()
        var month = (date.month+1).toString()
        var year = (date.year+1900).toString()
        if (day.length < 2)day = "0"+day
        if (month.length < 2)month = "0"+month
        return "%s/%s/%s".format(day,month,year)
    }

    private fun getFormatedDate(_day : Int, _month : Int, _year : Int) : String{
        var day = (_day).toString()
        var month = (_month).toString()
        var year = (_year).toString()
        if (day.length < 2)day = "0"+day
        if (month.length < 2)month = "0"+month
        return "%s/%s/%s".format(day,month,year)
    }

}