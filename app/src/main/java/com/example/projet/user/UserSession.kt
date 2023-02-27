package com.example.projet.user

import android.service.autofill.UserData

class UserSession {
    companion object{
        var logged : Boolean = false
        lateinit var user: UserDataModel
        var admin : Boolean = false


        public fun login(userDataModel : UserDataModel){
            user = userDataModel
            logged = true
            admin = user.role.equals("admin")
        }

        public fun logout(){
            logged = false
            admin = false
        }


    }
}