package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projet.DatabaseHelper.Companion.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val database = DatabaseHelper.database
}