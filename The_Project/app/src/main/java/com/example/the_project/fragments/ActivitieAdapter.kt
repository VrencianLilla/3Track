package com.example.the_project.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.the_project.R
import com.example.the_project.model.Activitie

class ActivitieAdapter (
    private var list: ArrayList<Activitie>,
    private val context: Context,
    ) :
    RecyclerView.Adapter<ActivitieAdapter.ActivitieViewHolder>() {

        inner class ActivitieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val typeTextView: TextView = itemView.findViewById(R.id.activities_type)
            val createdbyuseridTextView: TextView = itemView.findViewById(R.id.activities_createdbyuserid)
            val subtypeTextView: TextView = itemView.findViewById(R.id.activities_subtype)

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitieViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activitie_item, parent, false)
            return ActivitieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ActivitieViewHolder, position: Int) {
            val currentItem = list[position]

            holder.typeTextView.text = currentItem.type.toString()
            holder.createdbyuseridTextView.text = currentItem.created_by_user_ID.toString()
            holder.subtypeTextView.text = currentItem.sub_type.toString()

        }

        override fun getItemCount() = list.size

        fun setData(newlist: ArrayList<Activitie>){
            list = newlist
        }
}