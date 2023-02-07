package com.example.projet.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.databinding.ReservationRecyclerViewItemBinding

class RecyclerViewAdapter(private val data : List<DataModel>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(private val binding : ReservationRecyclerViewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: DataModel){
                binding.listItem = item
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewTypeParams: Int): RecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = ReservationRecyclerViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}