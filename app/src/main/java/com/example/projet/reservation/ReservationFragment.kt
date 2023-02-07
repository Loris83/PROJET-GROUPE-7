package com.example.projet.reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.DatabaseHelper
import com.example.projet.databinding.FragmentReservationBinding
import com.google.firebase.database.*


class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    private lateinit var manager: RecyclerView.LayoutManager
    //lateinit var data: List<ReservationDataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentReservationBinding.inflate(layoutInflater)
        getUser("michelG83","azerty1234")
        getReservation()
        val data = listOf(ReservationDataModel("10h00","11h00","Lundi 6 février","2", "1"))

        manager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.apply {
            adapter = RecyclerViewAdapter(data)
            layoutManager = manager
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
        //return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    fun getReservation(){
        DatabaseHelper.database.getReference("reservation")
            .orderByChild("date")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        //var position = 0
                        /*
                        while(snapshot.children.elementAt(position).exists()) {
                            val reservation =
                                snapshot.children.elementAt(position).getValue(ReservationDataModel::class.java)
                            Log.d("fireBase", "" + reservation.toString())
                            position+=1
                        }
                        */
                         val resa = snapshot.children.map {
                             it.getValue(ReservationDataModel::class.java)
                         }
                        Log.d("dataBase", "" + resa)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }
            })
    }

    fun getUser(username: String, password: String) {
        DatabaseHelper.database.getReference("user")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("dataBase", snapshot.toString())
                    if(snapshot.exists()) {
                        val user = snapshot.children.elementAt(1).getValue(User::class.java)
                        //if(user?.password == password) {
                        Log.d("dataBase",""+user.toString())
                            // Connected
                        //}
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }

            })
    }

    @IgnoreExtraProperties
    data class User(val username: String? = null,
                    val last_name: String? = null,
                    val first_name: String? = null,
                    val role: String? = null,
                    val password: String? = null,
                    val validated: Boolean? = null,
                    val uuid: String? = null) {
        // Null default values create a no-argument default constructor, which is needed
        // for deserialization from a DataSnapshot.
    }

}