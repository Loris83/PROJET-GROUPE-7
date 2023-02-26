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
        getReservation()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
        //return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

     fun getReservation()  {
            DatabaseHelper.database.getReference("reservation")
                .orderByChild("date")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val reservation = snapshot.children.map {
                                it.getValue(ReservationDataModel::class.java)
                            }
                            data = reservation as List<ReservationDataModel>
                            manager = LinearLayoutManager(binding.root.context)
                            binding.recyclerView.apply {
                                adapter = RecyclerViewAdapter(data)
                                layoutManager = manager
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }
                })
    }

}