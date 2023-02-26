package com.example.projet.userlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.DatabaseHelper


import com.example.projet.databinding.UserItemBinding
import com.example.projet.user.UserDataModel
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UserListAdapter(private val userList: List<UserDataModel>)
    : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

        class ViewHolder(binding : UserItemBinding) : RecyclerView.ViewHolder(binding.root){
            val username : TextView = binding.username
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = UserItemBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.username.text = currentItem.username
    }

}