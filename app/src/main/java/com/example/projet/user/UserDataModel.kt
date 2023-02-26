package com.example.projet.user

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDataModel (  var username:String = "",
                            var role: String = "",
                            var password: String = "",
                            var verified : Boolean = false)