package com.example.projet.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projet.MainActivity
import com.example.projet.R
import com.example.projet.databinding.FragmentHomeBinding
import com.example.projet.user.UserSession
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }




}