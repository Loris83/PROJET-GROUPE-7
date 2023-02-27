package com.example.projet.reservation

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ReservationDataModel(
        val id : String = "",
        val opening: String = "",
        val ending: String = "",
        val date: String = "",
        val field_id: String = "",
        val user_id: String = ""
){

}