package com.example.projet.user

import android.service.autofill.UserData

class UserSession {
    companion object{
        var logged : Boolean = false
        lateinit var userName : String
        lateinit var password : String
        var admin : Boolean = false

        public fun login(user : UserDataModel){
            userName = user.username
            password = user.password
            logged = true
            admin = user.role.equals("admin")
        }

        public fun logout(){
            logged = false
            admin = false
        }
    }
}