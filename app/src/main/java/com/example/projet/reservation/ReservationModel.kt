package com.example.projet.reservation

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet.DatabaseHelper
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.childEvents
import com.google.firebase.database.ktx.snapshots
import java.util.*

class ReservationModel {
    companion object {

        fun getReservations(){
            var reservations: List<ReservationDataModel> = listOf()

        }

        fun findReservation(action : (List<ReservationDataModel>)-> Unit){
            DatabaseHelper.database.getReference("reservation")
                .orderByChild("date")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val reservation = snapshot.children.map {
                                it.getValue(ReservationDataModel::class.java)
                            }
                            val data = reservation as List<ReservationDataModel>
                            action(data)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }
                })

        }

        fun addReservation(reservation: ReservationDataModel){
            /*DatabaseHelper.database.reference.child("user")
                .child(UUID.randomUUID().toString())
                .setValue(user)*/
            DatabaseHelper.database.getReference("reservation")
                .orderByChild("date")
                .equalTo(reservation.date)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var good = true

                        if (snapshot.exists()) {
                            val reservations = snapshot.children.map {
                                it.getValue(ReservationDataModel::class.java)
                            }

                            reservations.forEach {
                                it?.let {
                                    if (it.opening.equals(reservation.opening) && !it.field_id.equals(reservation.field_id))good = false
                                }
                            }

                        }

                        if(good){
                            DatabaseHelper.database.reference.child("reservation")
                                .child(UUID.randomUUID().toString())
                                .setValue(reservation)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }
                })
        }

        fun deleteReservation(reservation: ReservationDataModel){
            DatabaseHelper.database.reference.child("reservation").orderByChild("id")
                .equalTo(reservation.id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val reservations = snapshot.children.map {
                            DatabaseHelper.database.reference.child("reservation").child(it.key?:"").removeValue()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }
                })
        }
    }

}