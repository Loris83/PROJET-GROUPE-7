package com.example.projet

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DatabaseHelper {
    companion object{
        val database = Firebase.database("https://projetg7-f5d93-default-rtdb.europe-west1.firebasedatabase.app").reference
    }
}