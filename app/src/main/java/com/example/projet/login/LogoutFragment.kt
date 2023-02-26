package com.example.projet.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projet.MainActivity
import com.example.projet.R
import com.example.projet.databinding.FragmentLogoutBinding
import com.example.projet.user.UserSession


class LogoutFragment : Fragment() {
    private lateinit var binding : FragmentLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogoutBinding.inflate(layoutInflater)
        UserSession.logout()
        MainActivity.setupMenu()
        findNavController().navigate(R.id.nav_home)
        return binding.root
    }


}