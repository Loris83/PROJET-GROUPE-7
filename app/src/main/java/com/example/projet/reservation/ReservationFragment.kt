package com.example.projet.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.databinding.FragmentReservationBinding


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    private lateinit var manager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentReservationBinding.inflate(layoutInflater)
        var data = listOf(DataModel(1,"Lundi 6 février","15h00","17h00", "Terrain 2"),
            DataModel(2,"Mardi 7 février","9h00","11h00", "Terrain 2"),
            DataModel(3,"Mercredi 8 février","7h00","8h00", "Terrain 1"),
            DataModel(4,"Jeudi 9 février","20h00","22h00", "Terrain 1"),
            DataModel(5,"Vendredi 10 février","15h00","17h00", "Terrain 1"))
        manager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.apply {
            adapter = RecyclerViewAdapter(data)
            layoutManager = manager
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
        //return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

}