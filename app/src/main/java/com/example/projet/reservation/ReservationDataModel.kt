package com.example.projet.reservation

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ReservationDataModel(
        val opening: String? = null,
        val ending: String? = null,
        val date: String? = null,
        val field_id: String? = null,
        val user_id: String? = null
){

}