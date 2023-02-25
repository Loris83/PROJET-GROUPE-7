package com.example.projet.reserver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.projet.R
import com.example.projet.databinding.FragmentBookingBinding


class BookingFragment : Fragment() {
    private lateinit var binding: FragmentBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBookingBinding.inflate(layoutInflater)
        listOf<Pair<Button, Int>>(
            Pair(binding.button7h00,7),
            Pair(binding.button8h00,8),
            Pair(binding.button9h00,9),
            Pair(binding.button10h00,10),
            Pair(binding.button11h00,11),
            Pair(binding.button12h00,12),
            Pair(binding.button13h00,13),
            Pair(binding.button14h00,14),
            Pair(binding.button15h00,15),
            Pair(binding.button16h00,16),
            Pair(binding.button17h00,17),
            Pair(binding.button18h00,18),
            Pair(binding.button19h00,19),
            Pair(binding.button20h00,20),
            Pair(binding.button21h00,21)
        ).forEach { horaire ->
            horaire.first.setOnClickListener {
                
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}