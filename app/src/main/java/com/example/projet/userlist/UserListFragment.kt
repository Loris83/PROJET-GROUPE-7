package com.example.projet.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.databinding.FragmentUserListBinding
import com.example.projet.reservation.RecyclerViewAdapter
import com.example.projet.user.UserModel


class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserListBinding.inflate(layoutInflater)
        var manager = LinearLayoutManager(binding.root.context)

        binding.recyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(true))
            layoutManager = manager
        }

        manager = LinearLayoutManager(binding.root.context)
        binding.secondRecyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(false))
            layoutManager = manager
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
        //return inflater.inflate(R.layout.fragment_user_list, container, false)
    }


}