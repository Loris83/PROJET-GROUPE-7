package com.example.projet.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.DatabaseHelper
import com.example.projet.R
import com.example.projet.databinding.FragmentUserListBinding
import com.example.projet.databinding.UserItemBinding
import com.example.projet.reservation.RecyclerViewAdapter
import com.example.projet.user.UserDataModel
import com.example.projet.user.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private lateinit var bindingBis: UserItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserListBinding.inflate(layoutInflater)
        bindingBis = UserItemBinding.inflate(layoutInflater)
        updateUserStatus()
        //var manager = LinearLayoutManager(binding.root.context)

        /*binding.recyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(true))
            layoutManager = manager
        }

        manager = LinearLayoutManager(binding.root.context)
        binding.secondRecyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(false))
            layoutManager = manager
        }*/
        UserModel.findVerifiedUsers(
            true,
        ) { users ->
            binding.secondRecyclerView.apply {
                adapter = UserListAdapter(users)
                layoutManager = LinearLayoutManager(binding.root.context)
            }
        }

        UserModel.findVerifiedUsers(
            false,
        ) { users ->
            binding.recyclerView.apply {
                adapter = UserListAdapter(users)
                layoutManager = LinearLayoutManager(binding.root.context)
            }
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
    fun getVerifiedUsers() {
        var users: MutableList<UserDataModel> = mutableListOf<UserDataModel>()
        DatabaseHelper.database.getReference("user")
            .orderByChild("verified")
            .equalTo(true)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        var index = 0
                        Log.d("children", "" + snapshot.childrenCount)
                        while (index < snapshot.childrenCount) {
                            var user = snapshot.children.elementAt(index)
                                .getValue(UserDataModel::class.java)
                            Log.d("children", "user : " + user)
                            if (user != null) {
                                users.add(user)
                                Log.d("children", "users : " + users[index])
                            }
                            index++
                        }
                        binding.secondRecyclerView.apply {
                            adapter = UserListAdapter(users)
                            layoutManager = LinearLayoutManager(binding.root.context)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }

            })
    }
    fun getUnverifiedUsers() {
        var users: MutableList<UserDataModel> = mutableListOf<UserDataModel>()
        DatabaseHelper.database.getReference("user")
            .orderByChild("verified")
            .equalTo(false)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        var index = 0
                        Log.d("children", "" + snapshot.childrenCount)
                        while (index < snapshot.childrenCount) {
                            var user = snapshot.children.elementAt(index)
                                .getValue(UserDataModel::class.java)
                            Log.d("children", "user : " + user)
                            if (user != null) {
                                users.add(user)
                                Log.d("children", "users : " + users[index])
                            }
                            index++
                        }
                        binding.recyclerView.apply {
                            adapter = UserListAdapter(users)
                            layoutManager = LinearLayoutManager(binding.root.context)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }

            })
    }

    fun updateUserStatus(){
            DatabaseHelper.database.getReference("user")
                .orderByChild("username")
                .equalTo(bindingBis.username.text.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            bindingBis.button2.setOnClickListener {
                                DatabaseHelper.database.reference.child("user").child("verified")
                                    .setValue(true)

                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }

                })
    }



}