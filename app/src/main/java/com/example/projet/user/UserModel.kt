package com.example.projet.user

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projet.DatabaseHelper
import com.example.projet.MainActivity
import com.example.projet.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class UserModel {
    companion object{

        fun findUser(username: String, password: String,action : (UserDataModel?) -> Unit, otherwise : () -> Unit) {
            DatabaseHelper.database.getReference("user")
                .orderByChild("username")
                .equalTo(username)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.d("dataBase", snapshot.toString())
                        if (snapshot.exists()) {
                            val user = snapshot.children.first().getValue(UserDataModel::class.java)
                            action(user)
                        } else {
                            otherwise()
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }

                })
        }

        fun addUser(user : UserDataModel){
            DatabaseHelper.database.reference.child("user")
                .child(UUID.randomUUID().toString())
                .setValue(user)
        }

        fun findVerifiedUsers(verified: Boolean, action : (MutableList<UserDataModel>) -> Unit){
            var users: MutableList<UserDataModel> = mutableListOf<UserDataModel>()
            DatabaseHelper.database.getReference("user")
                .orderByChild("verified")
                .equalTo(verified)
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
                            action(users)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }

                })
        }
    }


}