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

        fun getUsers() : List<UserDataModel> {
            var users : MutableList<UserDataModel>  = mutableListOf<UserDataModel>()
            /*users.add(
                UserDataModel(
                    R.drawable.a,
                    "Amber",
                    "vyx984@gmail.com",
                    true
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.b,
                    "Liam",
                    "vyx944@gmail.com",
                    false
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.c,
                    "Mia",
                    "johndoe123@gmail.com",
                    true
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.d,
                    "Aiden",
                    "vyx984@gmail.com",
                    false
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.e,
                    "Charlotte",
                    "smithjane456@yahoo.com",
                    true
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.f,
                    "Jackson",
                    "c9j3q1@aol.com",
                    false
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.g,
                    "Sophia",
                    "dkc621@hotmail.com",
                    true
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.h,
                    "Noah",
                    "j2m7lk@yahoo.com",
                    false
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.i,
                    "Stopia",
                    "michael88@outlook.com",
                    true
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.j,
                    "Ethan",
                    "charlotte7@hotmail.com",
                    false
                )
            )
            users.add(
                UserDataModel(
                    R.drawable.k,
                    "Ava",
                    "jennifer.brown@aol.com",
                    true
                )
            )*/
            return users
        }

        fun getVerifiedUsers(verified : Boolean) : List<UserDataModel> {
            return getUsers().filter {
                it.verified == verified
            }
        }

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