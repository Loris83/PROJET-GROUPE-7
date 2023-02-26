package com.example.projet.user

import android.util.Log
import android.widget.Toast
import com.example.projet.DatabaseHelper
import com.example.projet.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class UserModel {
    companion object{

        fun getUsers() : List<UserDataModel> {
            var users : MutableList<UserDataModel>  = mutableListOf<UserDataModel>()
            DatabaseHelper.database.getReference("user")
                .orderByChild("username")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()) {
                            var index = 0
                            Log.d("children",""+snapshot.childrenCount)
                            while (index < snapshot.childrenCount) {
                                var user = snapshot.children.elementAt(index).getValue(UserDataModel::class.java)
                                Log.d("children","user : "+user)
                                if (user != null) {
                                    users.add(user)
                                    Log.d("children","users : "+users[index])
                                }
                                index++
                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }

                })
            Log.d("children","users oof : "+users)
            return users
        }

        fun getVerifiedUsers(verified : Boolean) : List<UserDataModel> {
            return getUsers().filter {
                it.verified == verified
            }
        }
    }
//USERMODEL N'EST PAS UTILISÉ

}