package com.example.projet.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.example.projet.databinding.UserItemBinding
import com.example.projet.user.UserDataModel
import com.google.android.material.imageview.ShapeableImageView

class UserListAdapter(private val userList: List<UserDataModel>)
    : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

        class ViewHolder(binding : UserItemBinding) : RecyclerView.ViewHolder(binding.root){
            val image: ShapeableImageView = binding.userImage
            val name : TextView = binding.username
            val email : TextView = binding.email
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
        holder.image.setImageResource(currentItem.image)
        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
    }

}