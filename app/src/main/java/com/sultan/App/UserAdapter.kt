package com.sultan.App

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sultan.App.data.UserItem

class UserAdapter(val context: Context, val userList: List<UserItem>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var email : TextView


        init {
            name = itemView.findViewById(R.id.name)
            email = itemView.findViewById(R.id.email)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.showitem , parent , false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.email.text = userList[position].email

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}