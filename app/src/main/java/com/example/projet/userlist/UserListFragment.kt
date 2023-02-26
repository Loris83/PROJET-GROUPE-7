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
import com.example.projet.reservation.RecyclerViewAdapter
import com.example.projet.user.UserDataModel
import com.example.projet.user.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserListBinding.inflate(layoutInflater)
        var manager = LinearLayoutManager(binding.root.context)

        /*binding.recyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(true))
            layoutManager = manager
        }

        manager = LinearLayoutManager(binding.root.context)
        binding.secondRecyclerView.apply {
            adapter = UserListAdapter(UserModel.getVerifiedUsers(false))
            layoutManager = manager
        }*/
        getVerifiedUsers()
        getUnverifiedUsers()

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



}