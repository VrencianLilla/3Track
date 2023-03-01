package com.example.the_project.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.the_project.R
import com.example.the_project.model.User

class UserAdapter(
    private var list: ArrayList<User>,
    private val context: Context
    ) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lastnameTextView: TextView = itemView.findViewById(R.id.users_last_name)
        val firstnameTextView: TextView = itemView.findViewById(R.id.users_last_name)
        val emailTextView: TextView = itemView.findViewById(R.id.users_email)
        val locationTextView: TextView = itemView.findViewById(R.id.user_location)
        val phonenumberTextView: TextView = itemView.findViewById(R.id.users_phone_number)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.groups_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = list[position]

        holder.lastnameTextView.text = currentItem.last_name
        holder.firstnameTextView.text = currentItem.first_name
        holder.emailTextView.text = currentItem.email
        holder.locationTextView.text = currentItem.location
        holder.phonenumberTextView.text = currentItem.phone_number

    }

    override fun getItemCount() = list.size

    fun setData(newlist: ArrayList<User>){
        list = newlist
    }


}