package com.example.projet.user

import com.example.projet.R

class UserModel {
    companion object{

        fun getUsers() : List<UserDataModel> {
            var users : MutableList<UserDataModel>  = mutableListOf<UserDataModel>()
            users.add(
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
            )
            return users
        }

        fun getVerifiedUsers(verified : Boolean) : List<UserDataModel> {
            return getUsers().filter {
                it.verified == verified
            }
        }
    }


}