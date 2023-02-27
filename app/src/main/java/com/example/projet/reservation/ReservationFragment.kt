package com.example.projet.reservation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.DatabaseHelper
import com.example.projet.databinding.FragmentReservationBinding
import com.example.projet.user.UserSession
import com.google.firebase.database.*
import kotlinx.coroutines.runBlocking


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    private lateinit var manager: RecyclerView.LayoutManager
    var data: List<ReservationDataModel> = listOf()
    override fun onCreate(savedInstanceState: Bundle?): Unit = runBlocking {
        super.onCreate(savedInstanceState)
        binding = FragmentReservationBinding.inflate(layoutInflater)
        //getUser("michelG83","azerty1234")
        //getReservation()
        update()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
        //return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    private fun update(){
        ReservationModel.findReservation {
                data ->
            manager = LinearLayoutManager(binding.root.context)
            binding.recyclerView.apply {
                adapter = RecyclerViewAdapter(data.filter {
                    UserSession.admin || UserSession.user.username.equals(it.user_id)
                })
                layoutManager = manager
            }
        }
    }



}